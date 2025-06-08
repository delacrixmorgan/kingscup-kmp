package com.delacrixmorgan.kingscup.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CardRoot(viewModel: CardViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CardScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
    BackHandler { viewModel.onAction(navHostController, CardAction.OnCloseScreen) }
}

@Composable
fun CardScreen(
    state: CardUiState,
    onAction: (CardAction) -> Unit,
) {
    Row(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
    ) {
        RankSuit(suit = state.suit, rank = state.rank)

        Column(
            Modifier
                .weight(1F)
                .padding(vertical = 16.dp)
                .border(1.dp, MaterialTheme.colorScheme.outline, shape = RoundedCornerShape(8.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(Modifier.weight(1F), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                state.emoji?.let { Text(text = stringResource(it), style = MaterialTheme.typography.displayLarge, color = MaterialTheme.colorScheme.onPrimary) }
                Spacer(Modifier.height(16.dp))
                state.label?.let { Text(text = stringResource(it), style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onPrimary) }
                Spacer(Modifier.height(8.dp))
                state.description?.let { Text(text = stringResource(it), style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onPrimary) }
            }

            Button(
                content = { Text("Done") },
                onClick = { onAction(CardAction.OnCloseScreen) }
            )
        }

        RankSuit(suit = state.suit, rank = state.rank, inverted = true)
    }
}

@Composable
private fun RankSuit(
    suit: String,
    rank: String,
    inverted: Boolean = false
) {
    Column(
        modifier = Modifier.fillMaxHeight().padding(horizontal = 16.dp),
        verticalArrangement = if (inverted) Arrangement.Bottom else Arrangement.Top
    ) {
        Column(Modifier.rotate(if (inverted) 180F else 0F), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = rank, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimary)
            Text(text = suit, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.onPrimary)
        }
    }
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