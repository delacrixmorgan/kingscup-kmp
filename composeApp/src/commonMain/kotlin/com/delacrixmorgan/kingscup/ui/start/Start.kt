package com.delacrixmorgan.kingscup.ui.start

import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.ui.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StartScreen(
    state: StartUiState,
    onAction: (StartAction) -> Unit,
) {

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