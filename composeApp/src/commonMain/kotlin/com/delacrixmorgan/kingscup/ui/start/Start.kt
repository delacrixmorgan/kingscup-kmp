package com.delacrixmorgan.kingscup.ui.start

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowForward
import androidx.compose.material.icons.rounded.Public
import androidx.compose.material.icons.rounded.Share
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
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
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.app_name
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun StartRoot(viewModel: StartViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    StartScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun StartScreen(
    state: StartUiState,
    onAction: (StartAction) -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primaryContainer).weight(1.36F))
            Box(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondaryContainer).weight(1F))
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(Res.string.app_name),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(Modifier.height(36.dp))

            LargeFloatingActionButton(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                onClick = { onAction(StartAction.OnSetupClicked) },
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.AutoMirrored.Rounded.ArrowForward,
                    contentDescription = "Setup game"
                )
            }
            Spacer(Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                OutlinedButton(onClick = { onAction(StartAction.OnLocalisationClicked) }) {
                    Icon(
                        imageVector = Icons.Rounded.Public,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = "Change language",
                    )
                }
                OutlinedButton(onClick = { onAction(StartAction.OnSupportClicked) }) {
                    Icon(
                        imageVector = Icons.Rounded.Star,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = "Support and credits",
                    )
                }
                OutlinedButton(onClick = { onAction(StartAction.OnShareClicked) }) {
                    Icon(
                        imageVector = Icons.Rounded.Share,
                        tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        contentDescription = "Share with friends",
                    )
                }
            }
            Spacer(Modifier.height(16.dp))

            Text(
                modifier = Modifier.combinedClickable(
                    onClick = {},
                    onLongClick = { onAction(StartAction.OnVersionClicked) }
                ),
                text = state.version,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
            Spacer(Modifier.height(220.dp))
            // TODO (Banner - Translation Request)
        }
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