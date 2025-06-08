package com.delacrixmorgan.kingscup.ui.style

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class StyleViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(StyleUiState())
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
            initialValue = StyleUiState()
        )

    private fun loadData() {

    }

    fun onAction(navHostController: NavHostController, action: StyleAction) {

    }
}

data class StyleUiState(
    val closeScreen: Boolean = false,
)

sealed interface StyleAction {
    data object OnCloseScreen : StyleAction
}