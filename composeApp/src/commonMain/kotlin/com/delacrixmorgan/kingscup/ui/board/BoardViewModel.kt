package com.delacrixmorgan.kingscup.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.model.Card
import com.delacrixmorgan.kingscup.data.repository.DealerRepository
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class BoardViewModel(
    private val dealerRepository: DealerRepository
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(BoardUiState())
    val state = _state
        .onStart { loadData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = BoardUiState()
        )

    private fun loadData() {
        viewModelScope.launch {
            launch {
                dealerRepository.cards.collect { cards ->
                    _state.update { it.copy(cards = cards) }
                    logDebug()
                }
            }
            launch {
                dealerRepository.gameInSession.collect { gameInSession ->
                    _state.update { it.copy(gameInSession = gameInSession) }
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: BoardAction) {
        when (action) {
            is BoardAction.OnCardClicked -> {
                dealerRepository.drawCard(action.index)
                navHostController.navigate(Routes.Card)
            }
            BoardAction.OnCardDismissed -> {
                dealerRepository.discardCard()
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
            it.copy(debugText = "Deck Size: ${dealerRepository.cards.value.size}")
        }
    }
}

data class BoardUiState(
    val debugText: String = "",

    val cards: List<Card> = emptyList(),
    val gameInSession: Boolean = false,

    val closeScreen: Boolean = false,
    val showPauseBottomSheet: Boolean = false,
)

sealed interface BoardAction {
    data class OnCardClicked(val index: Int) : BoardAction
    data object OnCardDismissed : BoardAction

    data object OnPauseClicked : BoardAction
    data object OnPauseBottomSheetRestartClicked : BoardAction
    data object OnPauseBottomSheetQuitClicked : BoardAction
    data object OnPauseBottomSheetDismissed : BoardAction
    data object OnCloseScreen : BoardAction
}