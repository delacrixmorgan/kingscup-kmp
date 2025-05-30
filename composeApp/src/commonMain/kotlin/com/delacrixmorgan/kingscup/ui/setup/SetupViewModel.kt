package com.delacrixmorgan.kingscup.ui.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class SetupViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(SetupUiState())
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
            initialValue = SetupUiState()
        )

    private fun loadData() {

    }

    fun onAction(navHostController: NavHostController, action: SetupAction) {
        when (action) {
            is SetupAction.OnJokerSettingsToggled -> {
                _state.update { it.copy(jokersEnabled = action.enabled) }
            }
            SetupAction.OnStartClicked -> {
                navHostController.navigate(Routes.Loading)
            }
            SetupAction.OnThemeSelected -> {

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
    val closeScreen: Boolean = false,
)

sealed interface SetupAction {
    data class OnJokerSettingsToggled(val enabled: Boolean) : SetupAction
    data object OnThemeSelected : SetupAction
    data object OnRulesClicked : SetupAction

    data object OnStartClicked : SetupAction
    data object OnBackClicked : SetupAction
}