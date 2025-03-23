package com.delacrixmorgan.kingscup.ui.board

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BoardRoot(viewModel: BoardViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BoardScreen(state = state, onAction = viewModel::onAction)
}

@Composable
fun BoardScreen(
    state: BoardUiState,
    onAction: (BoardAction) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Board")
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        BoardScreen(
            state = BoardUiState(),
            onAction = {}
        )
    }
}