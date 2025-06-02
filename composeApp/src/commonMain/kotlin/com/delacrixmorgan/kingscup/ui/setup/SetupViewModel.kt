package com.delacrixmorgan.kingscup.ui.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.preferences.PreferencesRepository
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SetupViewModel(
    private val preferencesRepository: PreferencesRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(SetupUiState())
    val state = _state
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = SetupUiState()
        )

    private fun loadData() {
        viewModelScope.launch {
            launch {
                preferencesRepository.jokerEnabledFlow.collect { enabled ->
                    _state.update { it.copy(jokersEnabled = enabled) }
                }
            }
            launch {
                preferencesRepository.skinFlow.collect { skin ->
                    _state.update { it.copy(selectedSkin = skin) }
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: SetupAction) {
        when (action) {
            is SetupAction.OnJokerSettingsToggled -> {
                viewModelScope.launch {
                    preferencesRepository.saveJokerEnabled(action.enabled)
                }
            }
            SetupAction.OnStartClicked -> {
                navHostController.navigate(Routes.Loading)
            }
            is SetupAction.OnSkinSelected -> {
                viewModelScope.launch {
                    preferencesRepository.saveSkin(action.skin)
                }
            }
            SetupAction.OnRulesClicked -> {
                navHostController.navigate(Routes.Rules)
            }
            SetupAction.OnBackClicked -> {
                navHostController.navigateUp()
            }
        }
    }
}

data class SetupUiState(
    val jokersEnabled: Boolean = true,
    val selectedSkin: SkinPreference? = null,
    val closeScreen: Boolean = false,
)

sealed interface SetupAction {
    data class OnJokerSettingsToggled(val enabled: Boolean) : SetupAction
    data class OnSkinSelected(val skin: SkinPreference) : SetupAction
    data object OnRulesClicked : SetupAction

    data object OnStartClicked : SetupAction
    data object OnBackClicked : SetupAction
}