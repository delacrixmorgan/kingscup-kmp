package com.delacrixmorgan.kingscup.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.card.CardRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import org.jetbrains.compose.resources.StringResource

class CardViewModel(
    private val cardRepository: CardRepository
) : ViewModel() {

    companion object {
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
        val card = cardRepository.activeCard
        _state.update {
            it.copy(
                suit = card?.suit?.toString() ?: "",
                rank = card?.rank?.toString() ?: "",
                emoji = card?.rule?.emoji,
                label = card?.rule?.label,
                description = card?.rule?.description,
            )
        }
    }

    fun onAction(navHostController: NavHostController, action: CardAction) {
        when (action) {
            CardAction.OnCloseScreen -> {
                if (!state.value.hasNavigateUp) {
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
    val suit: String = "",
    val rank: String = "",
    val emoji: StringResource? = null,
    val label: StringResource? = null,
    val description: StringResource? = null,
    val hasNavigateUp: Boolean = false,

    val closeScreen: Boolean = false,
)

sealed interface CardAction {
    data object OnCloseScreen : CardAction
}