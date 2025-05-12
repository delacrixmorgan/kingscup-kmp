package com.delacrixmorgan.kingscup.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.repository.DealerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class CardViewModel(
    private val dealerRepository: DealerRepository
) : ViewModel() {

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
        val card = dealerRepository.previousCard
        _state.update {
            it.copy(debugText = "ID: ${card?.rule?.id} (${card?.suit?.name} - ${card?.rank?.name})")
        }
    }

    fun onAction(navHostController: NavHostController, action: CardAction) {
        when (action) {
            CardAction.OnCloseScreen -> {
                navHostController.popBackStack()
            }
        }
    }
}

data class CardUiState(
    val debugText: String = "",
    val closeScreen: Boolean = false,
)

sealed interface CardAction {
    data object OnCloseScreen : CardAction
}