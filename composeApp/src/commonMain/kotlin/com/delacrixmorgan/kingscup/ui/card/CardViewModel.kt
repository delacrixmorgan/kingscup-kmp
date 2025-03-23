package com.delacrixmorgan.kingscup.ui.card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class CardViewModel : ViewModel() {

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

    }

    fun onAction(action: CardAction) {

    }
}

data class CardUiState(
    val closeScreen: Boolean = false,
)

sealed interface CardAction {
    data object OnCloseScreen : CardAction
}