package com.delacrixmorgan.kingscup.ui.rules

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.component.BoxBackground
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun RulesRoot(viewModel: RulesViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    RulesScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun RulesScreen(
    state: RulesUiState,
    onAction: (RulesAction) -> Unit,
) {
    BoxBackground {
        Column(modifier = Modifier.padding(WindowInsets.systemBars.asPaddingValues())) {
            Row {
                NavigationBackIcon { onAction(RulesAction.OnBackClicked) }
            }
        }
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