package com.delacrixmorgan.kingscup.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.start.StartScreen
import com.delacrixmorgan.kingscup.ui.start.StartUiState

@Preview
@Composable
private fun StartPreview() {
    AppTheme {
        StartScreen(
            state = StartUiState(),
            onAction = {}
        )
    }
}