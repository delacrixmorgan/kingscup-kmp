package com.delacrixmorgan.kingscup.ui.appinfo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.nav.Routes
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

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
            is AppInfoAction.OpenDeveloperPage -> {
                _state.update { it.copy(openDeveloperPage = action.open) }
            }
            is AppInfoAction.OpenSourceCode -> {
                _state.update { it.copy(openSourceCode = action.open) }
            }
            AppInfoAction.OnStyleGuideClicked -> {
                navHostController.navigate(Routes.StyleGuide)
            }
        }
    }
}

data class AppInfoUiState(
    val closeScreen: Boolean = false,
    val openDeveloperPage: Boolean = false,
    val openSourceCode: Boolean = false,
)

sealed interface AppInfoAction {
    data object OnBackClicked : AppInfoAction
    data class OpenDeveloperPage(val open: Boolean) : AppInfoAction
    data class OpenSourceCode(val open: Boolean) : AppInfoAction
    data object OnStyleGuideClicked : AppInfoAction
}