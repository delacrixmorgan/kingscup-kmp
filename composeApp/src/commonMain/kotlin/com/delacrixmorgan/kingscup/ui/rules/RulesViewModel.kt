package com.delacrixmorgan.kingscup.ui.rules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class RulesViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(RulesUiState())
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
            initialValue = RulesUiState()
        )

    private fun loadData() {

    }

    fun onAction(action: RulesAction) {

    }
}

data class RulesUiState(
    val closeScreen: Boolean = false,
)

sealed interface RulesAction {
    data object OnCloseScreen : RulesAction
}