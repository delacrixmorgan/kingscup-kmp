package com.delacrixmorgan.kingscup.ui.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class StartViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(StartUiState())
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
            initialValue = StartUiState()
        )

    private fun loadData() {

    }

    fun onAction(action: StartAction) {

    }
}

data class StartUiState(
    val closeScreen: Boolean = false,
)

sealed interface StartAction {
    data object OnCloseScreen : StartAction
}