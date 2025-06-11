package com.delacrixmorgan.kingscup.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.component.AnimatedEmoji
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
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(WindowInsets.safeDrawing.asPaddingValues())
            .padding(vertical = 16.dp)
    ) {
        RankSuit(modifier = Modifier.align(Alignment.Start), suit = state.suit, rank = state.rank)

        Column(
            Modifier
                .fillMaxWidth()
                .weight(1F)
                .aspectRatio(63f / 88f)
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(24.dp)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(Modifier.weight(1F).padding(horizontal = 16.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                state.emoji?.let { AnimatedEmoji(stringResource(it)) }
                Spacer(Modifier.height(16.dp))
                state.label?.let { Text(text = stringResource(it), style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSecondary) }
                Spacer(Modifier.height(8.dp))
                state.description?.let { Text(text = stringResource(it), style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.onSecondary) }
            }

            LargeFloatingActionButton(
                containerColor = MaterialTheme.colorScheme.surface,
                contentColor = MaterialTheme.colorScheme.onSurface,
                shape = CircleShape,
                onClick = {
                    haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                    onAction(CardAction.OnCloseScreen)
                },
            ) {
                Icon(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Rounded.Done,
                    contentDescription = "Done"
                )
            }
            Spacer(Modifier.height(64.dp))
        }

        RankSuit(modifier = Modifier.align(Alignment.End), suit = state.suit, rank = state.rank, inverted = true)
    }
}

@Composable
private fun RankSuit(
    modifier: Modifier,
    suit: String,
    rank: String,
    inverted: Boolean = false
) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
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