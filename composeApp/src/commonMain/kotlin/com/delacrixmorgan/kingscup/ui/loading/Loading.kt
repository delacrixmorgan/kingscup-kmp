package com.delacrixmorgan.kingscup.ui.loading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun LoadingRoot(viewModel: LoadingViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    LoadingScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun LoadingScreen(
    state: LoadingUiState,
    onAction: (LoadingAction) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Loading")
    }

    LaunchedEffect(Unit) {
        delay(1_000L)
        onAction(LoadingAction.OpenBoardScreen)
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