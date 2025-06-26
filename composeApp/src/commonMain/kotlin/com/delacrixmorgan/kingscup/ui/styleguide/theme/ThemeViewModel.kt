package com.delacrixmorgan.kingscup.ui.styleguide.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.delacrixmorgan.kingscup.data.preferences.PreferencesRepository
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class ThemeViewModel(
    private val preferencesRepository: PreferencesRepository,
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(ThemeUiState())
    val state = _state
        .onStart { observeData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = ThemeUiState()
        )

    private fun observeData() {
        viewModelScope.launch {
            launch {
                preferencesRepository.themeFlow.collect { theme ->
                    _state.update { it.copy(selectedTheme = theme) }
                }
            }
            launch {
                preferencesRepository.skinFlow.collect { skin ->
                    _state.update { it.copy(selectedSkin = skin) }
                }
            }
        }
    }

    fun onAction(action: ThemeAction) {
        when (action) {
            is ThemeAction.OnThemeSelected -> viewModelScope.launch {
                preferencesRepository.saveTheme(action.theme)
            }
            is ThemeAction.OnSkinSelected -> viewModelScope.launch {
                preferencesRepository.saveSkin(action.skin)
            }
        }
    }
}

data class ThemeUiState(
    val selectedTheme: ThemePreference? = null,
    val selectedSkin: SkinPreference? = null,
)

sealed interface ThemeAction {
    data class OnThemeSelected(val theme: ThemePreference) : ThemeAction
    data class OnSkinSelected(val skin: SkinPreference) : ThemeAction
}