package com.delacrixmorgan.kingscup.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.card.CardRepository
import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.nav.Routes
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.board_tauntEight
import kingscup.composeapp.generated.resources.board_tauntFive
import kingscup.composeapp.generated.resources.board_tauntFour
import kingscup.composeapp.generated.resources.board_tauntNine
import kingscup.composeapp.generated.resources.board_tauntOne
import kingscup.composeapp.generated.resources.board_tauntSeven
import kingscup.composeapp.generated.resources.board_tauntSix
import kingscup.composeapp.generated.resources.board_tauntTen
import kingscup.composeapp.generated.resources.board_tauntThree
import kingscup.composeapp.generated.resources.board_tauntTwo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource
import org.koin.core.component.KoinComponent

class BoardViewModel(
    private val cardRepository: CardRepository
) : ViewModel(), KoinComponent {

    private val _state = MutableStateFlow(BoardUiState())
    val state = _state
        .onStart { observeData() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000L),
            initialValue = BoardUiState()
        )

    private val taunts by lazy {
        listOf(
            Res.string.board_tauntOne,
            Res.string.board_tauntTwo,
            Res.string.board_tauntThree,
            Res.string.board_tauntFour,
            Res.string.board_tauntFive,
            Res.string.board_tauntSix,
            Res.string.board_tauntSeven,
            Res.string.board_tauntEight,
            Res.string.board_tauntNine,
            Res.string.board_tauntTen,
        )
    }

    private fun observeData() {
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
                cardRepository.jokers.collectLatest { jokers ->
                    _state.update { it.copy(jokers = jokers) }
                }
            }
            launch {
                cardRepository.kingCounter.collectLatest { kingCounter ->
                    _state.update {
                        it.copy(
                            kingCounter = kingCounter,
                            hasGameEnded = kingCounter >= 4
                        )
                    }
                }
            }
        }
    }

    fun onAction(navHostController: NavHostController, action: BoardAction) {
        when (action) {
            is BoardAction.OnCardClicked -> viewModelScope.launch {
                if (!state.value.hasCardClicked && !state.value.hasGameEnded) {
                    _state.update { it.copy(hasCardClicked = true) }
                    cardRepository.drawCard(action.index)
                    navHostController.navigate(Routes.Card) {
                        launchSingleTop = true
                        popUpTo(Routes.Card) { inclusive = true }
                    }
                    delay(300)
                    _state.update { it.copy(hasCardClicked = false) }
                }
            }
            BoardAction.OnCardDismissed -> {
                if (cardRepository.activeCard != null) {
                    cardRepository.discardCard()
                    shuffleTaunt()
                }
                if (cardRepository.activeJoker != null) {
                    cardRepository.dismissJoker()
                }
            }
            is BoardAction.OnJokerClicked -> viewModelScope.launch {
                if (!state.value.hasCardClicked && !state.value.hasGameEnded) {
                    _state.update { it.copy(hasCardClicked = true) }
                    cardRepository.showJoker(action.card)
                    navHostController.navigate(Routes.Card) {
                        launchSingleTop = true
                        popUpTo(Routes.Card) { inclusive = true }
                    }
                    delay(300)
                    _state.update { it.copy(hasCardClicked = false) }
                }
            }
            BoardAction.OnPauseClicked -> {
                _state.update { it.copy(showPauseBottomSheet = true) }
            }
            BoardAction.OnPauseBottomSheetDismissed -> {
                _state.update { it.copy(showPauseBottomSheet = false) }
            }
            BoardAction.OnRestartClicked -> viewModelScope.launch {
                _state.update { it.copy(showPauseBottomSheet = false) }
                delay(100)
                navHostController.navigate(Routes.Loading) { popUpTo(Routes.Loading) { inclusive = false } }
            }
            BoardAction.OnExitClicked -> viewModelScope.launch {
                _state.update { it.copy(showPauseBottomSheet = false) }
                delay(100)
                navHostController.navigate(Routes.Start) { popUpTo(Routes.Start) { inclusive = true } }
            }
            BoardAction.OnCloseScreen -> {
                navHostController.navigateUp()
            }
        }
    }

    private fun shuffleTaunt() {
        _state.update { it.copy(taunt = taunts.random()) }
    }
}

data class BoardUiState(
    val cards: List<Card> = emptyList(),
    val taunt: StringResource? = null,
    val gameInSession: Boolean = false,
    val kingCounter: Int = 0,
    val jokerEnabled: Boolean = true,
    val jokers: List<Card> = emptyList(),
    val hasGameEnded: Boolean = false,

    val hasCardClicked: Boolean = false,
    val closeScreen: Boolean = false,
    val showPauseBottomSheet: Boolean = false,
)

sealed interface BoardAction {
    data class OnCardClicked(val index: Int) : BoardAction
    data object OnCardDismissed : BoardAction
    data class OnJokerClicked(val card: Card) : BoardAction

    data object OnPauseClicked : BoardAction
    data object OnPauseBottomSheetDismissed : BoardAction
    data object OnRestartClicked : BoardAction
    data object OnExitClicked : BoardAction
    data object OnCloseScreen : BoardAction
}