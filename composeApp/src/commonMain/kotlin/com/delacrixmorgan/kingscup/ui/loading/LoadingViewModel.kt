package com.delacrixmorgan.kingscup.ui.loading

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class LoadingViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(LoadingUiState())
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
            initialValue = LoadingUiState()
        )

    private fun loadData() {

    }

    fun onAction(action: LoadingAction) {

    }
}

data class LoadingUiState(
    val closeScreen: Boolean = false,
)

sealed interface LoadingAction {
    data object OnCloseScreen : LoadingAction
}