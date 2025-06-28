package com.delacrixmorgan.kingscup.ui.board

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.card.CardViewModel
import com.delacrixmorgan.kingscup.ui.component.AppScaffold
import com.delacrixmorgan.kingscup.ui.component.BouncyLazyRow
import com.delacrixmorgan.kingscup.ui.component.Confetti
import com.delacrixmorgan.kingscup.ui.component.CrownList
import com.delacrixmorgan.kingscup.ui.component.NavigationPauseIcon
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BoardRoot(viewModel: BoardViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    BoardScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
    BackHandler { viewModel.onAction(navHostController, BoardAction.OnPauseClicked) }

    val onCardDismissed = navHostController.currentBackStackEntry
        ?.savedStateHandle
        ?.getStateFlow(CardViewModel.ON_CARD_DISMISSED, false)
    LaunchedEffect(onCardDismissed?.value) {
        if (onCardDismissed?.value == true) {
            viewModel.onAction(navHostController, BoardAction.OnCardDismissed)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun BoardScreen(
    state: BoardUiState,
    onAction: (BoardAction) -> Unit,
) {
    val lazyListState = rememberLazyListState()
    AppScaffold(
        topBar = {
            Row(
                Modifier.padding(top = WindowInsets.systemBars.asPaddingValues().calculateTopPadding()),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(16.dp))
                CrownList(counter = state.kingCounter)
                Spacer(Modifier.weight(1F))
                if (!state.hasGameEnded) {
                    NavigationPauseIcon(onClicked = { onAction(BoardAction.OnPauseClicked) })
                }
            }
        },
        content = { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                BoardStatusSection(state, onAction)
                BouncyLazyRow(
                    modifier = Modifier.padding(vertical = 32.dp),
                    state = lazyListState,
                    cards = state.cards,
                    animateBounce = state.gameInSession,
                    onItemClicked = { onAction(BoardAction.OnCardClicked(it)) }
                )
            }
        }
    )
    if (state.hasGameEnded) {
        Confetti()
    }
    if (state.showPauseBottomSheet) {
        BoardPauseDialog(onAction)
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