package com.delacrixmorgan.kingscup.ui.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material.icons.rounded.LocalDrink
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.backhandler.BackHandler
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.component.AnimatedEmoji
import com.delacrixmorgan.kingscup.ui.component.AppBar
import com.delacrixmorgan.kingscup.ui.component.AppScaffold
import com.delacrixmorgan.kingscup.ui.component.Confetti
import com.delacrixmorgan.kingscup.ui.component.NavigationBackIcon
import com.delacrixmorgan.kingscup.ui.component.bounceClickEffect
import com.delacrixmorgan.kingscup.ui.extensions.getMaterialShape
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.card_buttonDone
import kingscup.composeapp.generated.resources.card_buttonGameOver
import kingscup.composeapp.generated.resources.rules_kingDescription
import kingscup.composeapp.generated.resources.rules_kingEmoji
import kingscup.composeapp.generated.resources.rules_kingLabel
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CardRoot(viewModel: CardViewModel, navHostController: NavHostController) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    CardScreen(state = state, onAction = { viewModel.onAction(navHostController, it) })
    BackHandler { viewModel.onAction(navHostController, CardAction.OnCloseScreen) }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
@Composable
fun CardScreen(
    state: CardUiState,
    onAction: (CardAction) -> Unit,
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    AppScaffold(
        topBar = { scrollBehavior ->
            AppBar(
                title = state.rank,
                navigationIcon = { NavigationBackIcon { onAction(CardAction.OnCloseScreen) } },
                scrollBehavior = scrollBehavior,
            )
        },
        content = { innerPadding ->
            Column(Modifier.padding(innerPadding)) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .aspectRatio(1F)
                        .background(color = MaterialTheme.colorScheme.secondaryContainer, shape = state.suit.getMaterialShape()),
                    contentAlignment = Alignment.Center
                ) {
                    state.emoji?.let { AnimatedEmoji(stringResource(it)) }
                }
                Spacer(Modifier.height(16.dp))

                Column(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    state.label?.let {
                        Text(
                            text = stringResource(it),
                            style = MaterialTheme.typography.displaySmall,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                    Spacer(Modifier.height(8.dp))
                    state.description?.let {
                        Text(
                            text = stringResource(it),
                            style = MaterialTheme.typography.titleLargeEmphasized,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    Spacer(Modifier.weight(1F))
                    val size = ButtonDefaults.ExtraLargeContainerHeight
                    Button(
                        modifier = Modifier
                            .heightIn(size)
                            .padding(horizontal = 16.dp)
                            .align(Alignment.CenterHorizontally)
                            .bounceClickEffect(),
                        contentPadding = ButtonDefaults.contentPaddingFor(size),
                        enabled = state.closeButtonEnabled,
                        onClick = {
                            haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                            onAction(CardAction.OnCloseScreen)
                        },
                    ) {
                        Icon(
                            imageVector = if (!state.hasGameEnded) Icons.Rounded.Done else Icons.Rounded.LocalDrink,
                            modifier = Modifier.size(ButtonDefaults.iconSizeFor(size)),
                            contentDescription = null
                        )
                        Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))
                        Text(
                            text = if (!state.hasGameEnded) stringResource(Res.string.card_buttonDone) else stringResource(Res.string.card_buttonGameOver),
                            style = ButtonDefaults.textStyleFor(size)
                        )
                    }
                    Spacer(Modifier.height(32.dp))
                }
            }
        }
    )
    if (state.hasGameEnded) {
        Confetti()
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        CardScreen(
            state = CardUiState(
                suit = Card.SuitType.Joker,
                rank = "K",
                emoji = Res.string.rules_kingEmoji,
                label = Res.string.rules_kingLabel,
                description = Res.string.rules_kingDescription,
            ),
            onAction = {}
        )
    }
}