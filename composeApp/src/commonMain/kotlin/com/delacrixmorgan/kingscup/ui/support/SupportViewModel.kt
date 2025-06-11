package com.delacrixmorgan.kingscup.ui.support

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class SupportViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(SupportUiState())
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
            initialValue = SupportUiState()
        )

    private fun loadData() {

    }

    fun onAction(navHostController: NavHostController, action: SupportAction) {
        when (action) {
            SupportAction.OnBackClicked -> {
                navHostController.navigateUp()
            }
        }
    }
}

data class SupportUiState(
    val closeScreen: Boolean = false,
)

sealed interface SupportAction {
    data object OnBackClicked : SupportAction
}