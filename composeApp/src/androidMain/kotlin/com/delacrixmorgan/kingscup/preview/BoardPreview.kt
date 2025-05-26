package com.delacrixmorgan.kingscup.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.board.BoardScreen
import com.delacrixmorgan.kingscup.ui.board.BoardUiState

@Preview(showBackground = true)
@Composable
private fun BoardPreview() {
    AppTheme {
        BoardScreen(
            state = BoardUiState(
                initialVisible = true,
            ),
            onAction = {}
        )
    }
}