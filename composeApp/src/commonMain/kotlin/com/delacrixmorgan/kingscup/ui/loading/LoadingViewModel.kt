package com.delacrixmorgan.kingscup.ui.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class LoadingViewModel : ViewModel() {

    private val _state = MutableStateFlow(LoadingUiState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LoadingUiState()
        )

    fun onAction(navHostController: NavHostController, action: LoadingAction) {
        when (action) {
            LoadingAction.OpenBoardScreen -> {
                navHostController.navigate(Routes.Board) { popUpTo(Routes.Start) { inclusive = true } }
            }
        }
    }
}

data class LoadingUiState(
    val closeScreen: Boolean = false,
)

sealed interface LoadingAction {
    data object OpenBoardScreen : LoadingAction
}