package com.delacrixmorgan.kingscup.ui.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

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
            SetupAction.OnRulesClicked -> {
                navHostController.navigate(Routes.Rules)
            }
            SetupAction.OnPlayClicked -> {
                navHostController.navigate(Routes.Loading)
            }
            SetupAction.OnCloseScreen -> {
                navHostController.navigateUp()
            }
        }
    }
}

data class SetupUiState(
    val closeScreen: Boolean = false,
)

sealed interface SetupAction {
    data object OnRulesClicked : SetupAction
    data object OnPlayClicked : SetupAction
    data object OnCloseScreen : SetupAction
}