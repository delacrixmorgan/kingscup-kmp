package com.delacrixmorgan.kingscup.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.card.CardRepository
import com.delacrixmorgan.kingscup.data.card.model.Card
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.rules_kingGameOverDescription
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.StringResource

class CardViewModel(
    private val cardRepository: CardRepository
) : ViewModel() {

    companion object {
        const val DELAY_CLOSE_BUTTON_ENABLED_IN_MS = 700L
        const val ON_CARD_DISMISSED = "onCardDismissed"
    }

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(CardUiState())
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
            initialValue = CardUiState()
        )

    private fun loadData() {
        val gameOver = cardRepository.gameOver.value
        val card = cardRepository.activeCard ?: cardRepository.activeJoker
        _state.update {
            it.copy(
                hasGameEnded = gameOver,
                suit = card?.suit,
                rank = card?.rank?.toString() ?: "",
                emoji = card?.rule?.emoji,
                label = card?.rule?.label,
                description = if (!gameOver) {
                    card?.rule?.description
                } else {
                    Res.string.rules_kingGameOverDescription
                },
            )
        }
        viewModelScope.launch {
            delay(DELAY_CLOSE_BUTTON_ENABLED_IN_MS)
            _state.update { it.copy(closeButtonEnabled = true) }
        }
    }

    fun onAction(navHostController: NavHostController, action: CardAction) {
        when (action) {
            CardAction.OnCloseScreen -> {
                if (!state.value.hasNavigateUp && state.value.closeButtonEnabled) {
                    _state.update { it.copy(hasNavigateUp = true) }
                    navHostController.previousBackStackEntry
                        ?.savedStateHandle
                        ?.set(ON_CARD_DISMISSED, true)
                    navHostController.navigateUp()
                }
            }
        }
    }
}

data class CardUiState(
    val suit: Card.SuitType? = null,
    val rank: String = "",
    val emoji: StringResource? = null,
    val label: StringResource? = null,
    val description: StringResource? = null,
    val hasNavigateUp: Boolean = false,

    val hasGameEnded: Boolean = false,
    val closeScreen: Boolean = false,
    val closeButtonEnabled: Boolean = false,
)

sealed interface CardAction {
    data object OnCloseScreen : CardAction
}