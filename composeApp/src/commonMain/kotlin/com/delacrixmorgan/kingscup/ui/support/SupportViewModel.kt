package com.delacrixmorgan.kingscup.ui.support

import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.support.model.Portfolio
import com.delacrixmorgan.kingscup.getVersionCode
import com.delacrixmorgan.kingscup.getVersionName
import com.delacrixmorgan.kingscup.nav.Routes
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
            SupportAction.OnAppInfoClicked -> {
                navHostController.navigate(Routes.AppInfo)
            }
            is SupportAction.OpenPrivacyPolicy -> {
                _state.update { it.copy(openPrivacyPolicy = action.open) }
            }
            is SupportAction.OpenSendFeedback -> {
                _state.update { it.copy(openSendFeedback = action.open) }
            }
            is SupportAction.OpenRateUs -> {
                _state.update { it.copy(openRateUs = action.open) }
            }
        }
    }
}

data class SupportUiState(
    val version: String = "",
    val portfolio: List<Portfolio> = Portfolio.entries,

    val openPrivacyPolicy: Boolean = false,
    val openSendFeedback: Boolean = false,
    val openRateUs: Boolean = false,
    val closeScreen: Boolean = false,
)

sealed interface SupportAction {
    data object OnAppInfoClicked : SupportAction
    data class OpenPrivacyPolicy(val open: Boolean) : SupportAction
    data class OpenSendFeedback(val open: Boolean) : SupportAction
    data class OpenRateUs(val open: Boolean) : SupportAction

    data object OnBackClicked : SupportAction
}