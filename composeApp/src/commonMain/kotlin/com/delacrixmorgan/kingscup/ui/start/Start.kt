package com.delacrixmorgan.kingscup.ui.start

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.delacrixmorgan.kingscup.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StartRoot(viewModel: StartViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    StartScreen(state = state, onAction = viewModel::onAction)
}

@Composable
fun StartScreen(
    state: StartUiState,
    onAction: (StartAction) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Start")
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        StartScreen(
            state = StartUiState(),
            onAction = {}
        )
    }
}