package com.delacrixmorgan.kingscup.ui.setup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun SetupRoot(viewModel: SetupViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SetupScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun SetupScreen(
    state: SetupUiState,
    onAction: (SetupAction) -> Unit,
) {
    Scaffold(
        content = {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Setup")
            }
        },
        bottomBar = {
            Column(
                Modifier
                    .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    content = { Text("Rules") },
                    onClick = { onAction(SetupAction.OnRulesClicked) }
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    content = { Text("Play") },
                    onClick = { onAction(SetupAction.OnPlayClicked) }
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        SetupScreen(
            state = SetupUiState(),
            onAction = {}
        )
    }
}