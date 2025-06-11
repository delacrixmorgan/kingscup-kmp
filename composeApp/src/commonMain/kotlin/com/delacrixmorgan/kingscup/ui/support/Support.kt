package com.delacrixmorgan.kingscup.ui.support

import androidx.compose.foundation.layout.Column
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
fun SupportRoot(viewModel: SupportViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SupportScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun SupportScreen(
    state: SupportUiState,
    onAction: (SupportAction) -> Unit,
) {
    BoxBackground {
        Column(modifier = Modifier.padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding())) {
            NavigationBackIcon { onAction(SupportAction.OnBackClicked) }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SupportScreen(
            state = SupportUiState(),
            onAction = {}
        )
    }
}