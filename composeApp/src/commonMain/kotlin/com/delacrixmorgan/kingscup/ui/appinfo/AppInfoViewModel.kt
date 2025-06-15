package com.delacrixmorgan.kingscup.ui.appinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn

class AppInfoViewModel : ViewModel() {

    private var hasLoadedInitialData = false

    private val _state = MutableStateFlow(AppInfoUiState())
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
            initialValue = AppInfoUiState()
        )

    private fun loadData() {

    }

    fun onAction(navHostController: NavHostController, action: AppInfoAction) {
        when (action) {
            AppInfoAction.OnBackClicked -> {
                navHostController.navigateUp()
            }
            AppInfoAction.OnDeveloperClicked -> TODO()
            AppInfoAction.OnSourceCodeClicked -> TODO()
            AppInfoAction.OnStyleGuideClicked -> {
                navHostController.navigate(Routes.StyleGuide)
            }
        }
    }
}

data class AppInfoUiState(
    val closeScreen: Boolean = false,
)

sealed interface AppInfoAction {
    data object OnBackClicked : AppInfoAction
    data object OnDeveloperClicked : AppInfoAction
    data object OnSourceCodeClicked : AppInfoAction
    data object OnStyleGuideClicked : AppInfoAction
}