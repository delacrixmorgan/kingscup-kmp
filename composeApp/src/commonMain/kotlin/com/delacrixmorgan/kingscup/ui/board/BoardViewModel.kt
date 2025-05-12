package com.delacrixmorgan.kingscup.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.repository.DealerRepository
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.koin.core.component.KoinComponent

class BoardViewModel(
    private val dealerRepository: DealerRepository
) : ViewModel(), KoinComponent {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(BoardUiState())
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
            initialValue = BoardUiState()
        )

    private fun loadData() {
        dealerRepository.buildDeck()
        logDebug()
    }

    fun onAction(navHostController: NavHostController, action: BoardAction) {
        when (action) {
            is BoardAction.OnCardClicked -> {
                // TODO (Save action.id to CardRepository)
                action.id
                navHostController.navigate(Routes.Card)
                logDebug()
            }
            BoardAction.OnPauseClicked -> {
                _state.update { it.copy(showPauseBottomSheet = true) }
            }
            BoardAction.OnPauseBottomSheetDismissed -> {
                _state.update { it.copy(showPauseBottomSheet = false) }
            }
            BoardAction.OnPauseBottomSheetRestartClicked -> {
                // TODO (Restart)
                _state.update { it.copy(showPauseBottomSheet = false) }
                navHostController.navigate(Routes.Loading) { popUpTo(Routes.Loading) { inclusive = false } }
            }
            BoardAction.OnPauseBottomSheetQuitClicked -> {
                navHostController.navigate(Routes.Start) { popUpTo(Routes.Start) { inclusive = true } }
            }
            BoardAction.OnCloseScreen -> {
                navHostController.navigateUp()
            }
        }
    }

    private fun logDebug() {
        _state.update {
            it.copy(debugText = "Deck Size: ${dealerRepository.deck.size}")
        }
    }
}

data class BoardUiState(
    val debugText: String = "",
    val closeScreen: Boolean = false,
    val showPauseBottomSheet: Boolean = false,
)

sealed interface BoardAction {
    data class OnCardClicked(val id: String) : BoardAction
    data object OnPauseClicked : BoardAction
    data object OnPauseBottomSheetRestartClicked : BoardAction
    data object OnPauseBottomSheetQuitClicked : BoardAction
    data object OnPauseBottomSheetDismissed : BoardAction
    data object OnCloseScreen : BoardAction
}