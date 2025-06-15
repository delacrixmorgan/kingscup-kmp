package com.delacrixmorgan.kingscup.ui.support

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.getVersionCode
import com.delacrixmorgan.kingscup.getVersionName
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SupportViewModel : ViewModel() {

    private val _state = MutableStateFlow(SupportUiState())
    val state: StateFlow<SupportUiState>
        get() = _state.asStateFlow()

    init {
        _state.update { it.copy(version = "${getVersionName()} (${getVersionCode()})") }
    }

    fun onAction(navHostController: NavHostController, action: SupportAction) {
        when (action) {
            SupportAction.OnBackClicked -> {
                navHostController.navigateUp()
            }
            SupportAction.OnAppInfoClicked -> TODO()
            SupportAction.OnPrivacyPolicyClicked -> TODO()
            SupportAction.OnSendFeedbackClicked -> TODO()
            SupportAction.OnRateUsClicked -> TODO()
        }
    }
}

data class SupportUiState(
    val version: String = "",
    val closeScreen: Boolean = false,
)

sealed interface SupportAction {
    data object OnAppInfoClicked : SupportAction
    data object OnPrivacyPolicyClicked : SupportAction
    data object OnSendFeedbackClicked : SupportAction
    data object OnRateUsClicked : SupportAction

    data object OnBackClicked : SupportAction
}