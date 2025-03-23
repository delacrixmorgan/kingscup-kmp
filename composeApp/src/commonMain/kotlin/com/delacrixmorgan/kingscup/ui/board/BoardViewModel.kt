package com.delacrixmorgan.kingscup.ui.board

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class BoardViewModel : ViewModel() {

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

    }

    fun onAction(action: BoardAction) {

    }
}

data class BoardUiState(
    val closeScreen: Boolean = false,
)

sealed interface BoardAction {
    data object OnCloseScreen : BoardAction
}