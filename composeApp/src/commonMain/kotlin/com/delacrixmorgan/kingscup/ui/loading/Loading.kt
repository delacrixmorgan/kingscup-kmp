package com.delacrixmorgan.kingscup.ui.loading

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.LoadingIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadingRoot(viewModel: LoadingViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadingScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalComposeUiApi::class)
@Composable
fun LoadingScreen(
    state: LoadingUiState,
    onAction: (LoadingAction) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.spacedBy(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            LoadingIndicator(modifier = Modifier.size(120.dp))
            Text(text = "Loading")
        }
    }
    BackHandler { }
    LaunchedEffect(state.openBoardScreen) {
        if (state.openBoardScreen) {
            delay(1_000L)
            onAction(LoadingAction.OpenBoardScreen)
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        LoadingScreen(
            state = LoadingUiState(),
            onAction = {}
        )
    }
}