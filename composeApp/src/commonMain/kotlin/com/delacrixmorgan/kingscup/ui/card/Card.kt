package com.delacrixmorgan.kingscup.ui.card

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CardRoot(viewModel: CardViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CardScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
}

@Composable
fun CardScreen(
    state: CardUiState,
    onAction: (CardAction) -> Unit,
) {
    Scaffold(
        content = {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Card", textAlign = TextAlign.Center)
                    Spacer(Modifier.height(16.dp))
                    Text(text = state.debugText, textAlign = TextAlign.Center)
                }
            }
        },
        bottomBar = {
            Column(
                Modifier
                    .padding(bottom = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding())
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    content = { Text("Done") },
                    onClick = { onAction(CardAction.OnCloseScreen) }
                )
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        CardScreen(
            state = CardUiState(),
            onAction = {}
        )
    }
}