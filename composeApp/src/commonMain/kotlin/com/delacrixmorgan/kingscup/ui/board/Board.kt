package com.delacrixmorgan.kingscup.ui.board

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.FreeBreakfast
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.card.CardViewModel
import com.delacrixmorgan.kingscup.ui.component.BouncyLazyRow
import com.delacrixmorgan.kingscup.ui.component.dashedCard
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
    Column(modifier = Modifier.fillMaxSize().padding(WindowInsets.systemBars.asPaddingValues())) {
        FilledIconButton(
            modifier = Modifier.padding(16.dp).size(64.dp).align(Alignment.End),
            onClick = { onAction(BoardAction.OnPauseClicked) }
        ) {
            Icon(
                imageVector = Icons.Rounded.FreeBreakfast,
                contentDescription = "Go back",
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

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Box(Modifier.dashedCard())
                Box(Modifier.dashedCard())
                Box(Modifier.dashedCard())
                Box(Modifier.dashedCard())
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