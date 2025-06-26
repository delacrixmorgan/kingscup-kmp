package com.delacrixmorgan.kingscup.ui.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.platform.Platform
import com.delacrixmorgan.kingscup.data.preferences.PreferencesRepository
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference
import com.delacrixmorgan.kingscup.nav.Routes
import com.delacrixmorgan.kingscup.platform
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StartViewModel(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {

    private val _state = MutableStateFlow(StartUiState())
    val state = _state
        .onStart { observeData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = StartUiState()
        )

    private fun observeData() {
        viewModelScope.launch {
            launch {
                preferencesRepository.localeFlow.collectLatest { locale ->
                    _state.update { it.copy(selectedLocale = locale) }
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: StartAction) {
        when (action) {
            StartAction.OnSetupClicked -> {
                navHostController.navigate(Routes.Setup)
            }
            StartAction.OnLocalisationClicked -> when (platform) {
                Platform.Android -> _state.update { it.copy(showLocalisationBottomSheet = true) }
                Platform.iOS -> _state.update { it.copy(openAppSettings = true) }
            }
            is StartAction.OnLocalisationChanged -> viewModelScope.launch {
                preferencesRepository.saveLocale(action.localePreference)
            }
            StartAction.OnLocalisationBottomSheetDismissed -> {
                _state.update { it.copy(showLocalisationBottomSheet = false) }
            }
            StartAction.OnLocalisationAppSettingsOpened -> {
                _state.update { it.copy(openAppSettings = false) }
            }
            StartAction.OnSupportClicked -> {
                navHostController.navigate(Routes.Support)
            }
            StartAction.OnCloseScreen -> {
                navHostController.navigateUp()
            }
        }
    }
}

data class StartUiState(
    val selectedLocale: LocalePreference = LocalePreference.Default,
    val locale: List<LocalePreference> = LocalePreference.entries,
    val showLocalisationBottomSheet: Boolean = false,
    val openAppSettings: Boolean = false,
)

sealed interface StartAction {
    data object OnSetupClicked : StartAction

    data object OnLocalisationClicked : StartAction
    data class OnLocalisationChanged(val localePreference: LocalePreference) : StartAction
    data object OnLocalisationBottomSheetDismissed : StartAction
    data object OnLocalisationAppSettingsOpened : StartAction

    data object OnSupportClicked : StartAction

    data object OnCloseScreen : StartAction
}