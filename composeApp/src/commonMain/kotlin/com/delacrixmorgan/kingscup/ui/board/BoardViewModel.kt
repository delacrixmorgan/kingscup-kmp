package com.delacrixmorgan.kingscup.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.card.CardRepository
import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent

class BoardViewModel(
    private val cardRepository: CardRepository
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
                cardRepository.cards.collectLatest { cards ->
                    _state.update { it.copy(cards = cards) }
                }
            }
            launch {
                cardRepository.gameInSession.collectLatest { gameInSession ->
                    _state.update { it.copy(gameInSession = gameInSession) }
                }
            }
            launch {
                cardRepository.kingCounter.collectLatest { kingCounter ->
                    _state.update { it.copy(kingCounter = kingCounter) }
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: BoardAction) {
        when (action) {
            is BoardAction.OnCardClicked -> {
                cardRepository.drawCard(action.index)
                navHostController.navigate(Routes.Card)
            }
            BoardAction.OnCardDismissed -> {
                cardRepository.discardCard()
            }
            BoardAction.OnPauseClicked -> {
                _state.update { it.copy(showPauseBottomSheet = true) }
            }
            BoardAction.OnPauseBottomSheetDismissed -> {
                _state.update { it.copy(showPauseBottomSheet = false) }
            }
            BoardAction.OnPauseBottomSheetRestartClicked -> {
                // TODO (Restart)
                viewModelScope.launch {
                    _state.update { it.copy(showPauseBottomSheet = false) }
                    delay(100)
                    navHostController.navigate(Routes.Loading) { popUpTo(Routes.Loading) { inclusive = false } }
                }
            }
            BoardAction.OnPauseBottomSheetQuitClicked -> {
                viewModelScope.launch {
                    _state.update { it.copy(showPauseBottomSheet = false) }
                    delay(100)
                    navHostController.navigate(Routes.Start) { popUpTo(Routes.Start) { inclusive = true } }
                }
            }
            BoardAction.OnCloseScreen -> {
                navHostController.navigateUp()
            }
        }
    }
}

data class BoardUiState(
    val cards: List<Card> = emptyList(),
    val gameInSession: Boolean = false,
    val kingCounter: Int = 0,

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