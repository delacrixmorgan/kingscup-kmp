package com.delacrixmorgan.kingscup.ui.rules

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
fun RulesRoot(viewModel: RulesViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RulesScreen(state = state, onAction = viewModel::onAction)
}

@Composable
fun RulesScreen(
    state: RulesUiState,
    onAction: (RulesAction) -> Unit,
) {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Rules")
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        RulesScreen(
            state = RulesUiState(),
            onAction = {}
        )
    }
}