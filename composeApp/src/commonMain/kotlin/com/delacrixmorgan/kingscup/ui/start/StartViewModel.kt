package com.delacrixmorgan.kingscup.ui.start

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class StartViewModel : ViewModel() {

    private val _state = MutableStateFlow(StartUiState())
    val state: StateFlow<StartUiState>
        get() = _state.asStateFlow()

    fun onAction(navHostController: NavHostController, action: StartAction) {
        when (action) {
            StartAction.OnSetupClicked -> {
                navHostController.navigate(Routes.Setup)
            }
            StartAction.OnLocalisationClicked -> {
                _state.update { it.copy(showLocalisationBottomSheet = true) }
            }
            is StartAction.OnLocalisationChanged -> {
                // TODO (Localisation)
            }
            StartAction.OnLocalisationBottomSheetDismissed -> {
                _state.update { it.copy(showLocalisationBottomSheet = false) }
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
    val showLocalisationBottomSheet: Boolean = false,
)

sealed interface StartAction {
    data object OnSetupClicked : StartAction

    data object OnLocalisationClicked : StartAction
    data class OnLocalisationChanged(val locale: Locale) : StartAction
    data object OnLocalisationBottomSheetDismissed : StartAction
    data object OnSupportClicked : StartAction

    data object OnCloseScreen : StartAction
}