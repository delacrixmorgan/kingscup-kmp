package com.delacrixmorgan.kingscup.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.FreeBreakfast
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.card.CardViewModel
import com.delacrixmorgan.kingscup.ui.component.BouncyLazyRow
import com.delacrixmorgan.kingscup.ui.component.dashedBorder
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BoardRoot(viewModel: BoardViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BoardScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })

    val onCardDismissed = navHostController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow(CardViewModel.ON_CARD_DISMISSED, false)
    LaunchedEffect(onCardDismissed?.value) {
        if (onCardDismissed?.value == true) {
            viewModel.onAction(navHostController, BoardAction.OnCardDismissed)
        }
    }
}

@Composable
fun BoardScreen(
    state: BoardUiState,
    onAction: (BoardAction) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    Column(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primaryContainer).padding(WindowInsets.systemBars.asPaddingValues())) {
        FilledIconButton(
            modifier = Modifier.padding(16.dp).size(64.dp).align(Alignment.End),
            onClick = { onAction(BoardAction.OnPauseClicked) }
        ) {
            Icon(
                imageVector = Icons.Rounded.FreeBreakfast,
                contentDescription = "Pause game",
            )
        }
        StatusSection(state)
        BouncyLazyRow(
            modifier = Modifier.padding(vertical = 32.dp),
            state = lazyListState,
            cards = state.cards,
            animateBounce = state.gameInSession,
            onItemClicked = { onAction(BoardAction.OnCardClicked(it)) }
        )
        Spacer(Modifier.height(56.dp))
    }

    if (state.showPauseBottomSheet) {
        Dialog(
            onDismissRequest = { onAction(BoardAction.OnPauseBottomSheetDismissed) },
            content = {
                Column(Modifier.background(MaterialTheme.colorScheme.primaryContainer, shape = RoundedCornerShape(24.dp)).padding(16.dp)) {
                    Text(
                        "Toilet Break",
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.headlineMedium,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(48.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        OutlinedButton(
                            modifier = Modifier.weight(1F),
                            onClick = { onAction(BoardAction.OnPauseBottomSheetRestartClicked) }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.RestartAlt,
                                contentDescription = "Restart game",
                            )
                        }
                        FilledIconButton(
                            modifier = Modifier.weight(1F),
                            colors = IconButtonDefaults.filledIconButtonColors(
                                containerColor = MaterialTheme.colorScheme.error,
                                contentColor = MaterialTheme.colorScheme.onError
                            ),
                            onClick = { onAction(BoardAction.OnPauseBottomSheetQuitClicked) }
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Quit game",
                            )
                        }
                    }
                }
            }
        )
    }
}

@Composable
private fun ColumnScope.StatusSection(state: BoardUiState) {
    Box(Modifier.fillMaxWidth().weight(1F), contentAlignment = Alignment.Center) {
        Column {
            Text(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = "${state.cards.size} cards left",
                style = MaterialTheme.typography.titleLarge,
            )
            Spacer(Modifier.height(16.dp))

            val cardWidth = 64.dp
            val cardRatio = 63f / 88f
            val totalKings = 4
            val activeKings = state.kingCounter.coerceIn(0, totalKings)
            val remainingKings = totalKings - activeKings

            val filledCardModifier = Modifier
                .width(cardWidth)
                .aspectRatio(cardRatio)
                .background(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )

            val emptyCardModifier = Modifier
                .width(cardWidth)
                .aspectRatio(cardRatio)
                .dashedBorder(
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    shape = RoundedCornerShape(12.dp)
                )

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                repeat(activeKings) {
                    Box(filledCardModifier)
                }
                repeat(remainingKings) {
                    Box(emptyCardModifier)
                }
            }

        }
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