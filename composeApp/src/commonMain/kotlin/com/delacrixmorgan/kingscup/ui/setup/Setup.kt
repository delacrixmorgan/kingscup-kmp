package com.delacrixmorgan.kingscup.ui.setup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer).padding(WindowInsets.systemBars.asPaddingValues())) {
        FilledIconButton(
            modifier = Modifier.padding(16.dp).size(64.dp).align(Alignment.Start),
            onClick = { onAction(SetupAction.OnBackClicked) }
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                contentDescription = "Go back",
            )
        }

        Spacer(Modifier.weight(1F))


        Button(
            modifier = Modifier.fillMaxWidth(),
            content = { Text("Jokers Enabled") },
            onClick = { onAction(SetupAction.OnStartClicked) }
        )

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            content = { Text("Rules") },
            onClick = { onAction(SetupAction.OnRulesClicked) }
        )

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            content = { Text("Theme") },
            onClick = { onAction(SetupAction.OnRulesClicked) }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            content = { Text("Play") },
            onClick = { onAction(SetupAction.OnStartClicked) }
        )
    }
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