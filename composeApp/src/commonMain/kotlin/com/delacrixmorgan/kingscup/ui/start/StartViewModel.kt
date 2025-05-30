package com.delacrixmorgan.kingscup.ui.start

import androidx.compose.ui.text.intl.Locale
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class StartViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(StartUiState())
    val state = _state
        .onStart {
            if (!hasLoadedInitialData) {
                hasLoadedInitialData = true
                loadData()
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = StartUiState()
        )

    private fun loadData() {

    }

    fun onAction(navHostController: NavHostController, action: StartAction) {
        when (action) {
            StartAction.OnSetupClicked -> {
                navHostController.navigate(Routes.Setup)
            }
            StartAction.OnLocalisationClicked -> {
                _state.update { it.copy(showLocalisationBottomSheet = true) }
            }
            is StartAction.OnLocalisationChanged -> {

            }
            StartAction.OnLocalisationBottomSheetDismissed -> {
                _state.update { it.copy(showLocalisationBottomSheet = false) }
            }
            StartAction.OnSupportClicked -> {
                navHostController.navigate(Routes.Support)
            }
            StartAction.OnShareClicked -> {
                // TODO (Share Sheet)
            }
            StartAction.OnCloseScreen -> {
                navHostController.navigateUp()
            }
        }
    }
}

data class StartUiState(
    val showLocalisationBottomSheet: Boolean = false,
    val closeScreen: Boolean = false,
)

sealed interface StartAction {
    data object OnSetupClicked : StartAction

    data object OnLocalisationClicked : StartAction
    data class OnLocalisationChanged(val locale: Locale) : StartAction
    data object OnLocalisationBottomSheetDismissed : StartAction
    data object OnSupportClicked : StartAction
    data object OnShareClicked : StartAction

    data object OnCloseScreen : StartAction
}