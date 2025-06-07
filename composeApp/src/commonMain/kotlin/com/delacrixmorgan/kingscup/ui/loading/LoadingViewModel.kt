package com.delacrixmorgan.kingscup.ui.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.nav.Routes
import com.delacrixmorgan.kingscup.usecase.BuildCardDeckUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoadingViewModel(
    private val buildCardDeckUseCase: BuildCardDeckUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(LoadingUiState())
    val state = _state
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = LoadingUiState()
        )

    init {
        viewModelScope.launch {
            val completed = buildCardDeckUseCase.firstOrNull()
            if (completed != null) {
                _state.update { it.copy(openBoardScreen = true) }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: LoadingAction) {
        when (action) {
            LoadingAction.OpenBoardScreen -> {
                navHostController.navigate(Routes.Board) { popUpTo(Routes.Start) { inclusive = true } }
            }
        }
    }
}

data class LoadingUiState(
    val openBoardScreen: Boolean = false,
)

sealed interface LoadingAction {
    data object OpenBoardScreen : LoadingAction
}