package com.delacrixmorgan.kingscup.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.RestartAlt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.ui.component.JokerList
import com.delacrixmorgan.kingscup.ui.component.bounceClickEffect
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.board_buttonExitGame
import kingscup.composeapp.generated.resources.board_buttonNewGame
import kingscup.composeapp.generated.resources.board_cardsLeft
import kingscup.composeapp.generated.resources.board_gameOver
import kingscup.composeapp.generated.resources.board_tauntOne
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ColumnScope.BoardStatusSection(
    state: BoardUiState,
    onAction: (BoardAction) -> Unit,
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    Box(Modifier.fillMaxWidth().weight(1F)) {
        if (state.jokerEnabled) {
            JokerList(
                jokers = state.jokers,
                onJokerClicked = { onAction(BoardAction.OnJokerClicked(it)) },
            )
        }
        Column(Modifier.align(Alignment.Center).padding(horizontal = 52.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.width(120.dp).aspectRatio(1F).background(MaterialTheme.colorScheme.secondaryContainer, MaterialShapes.Cookie12Sided.toShape()))
            Spacer(Modifier.height(16.dp))
            Text(
                text = if (!state.hasGameEnded) stringResource(state.taunt ?: Res.string.board_tauntOne) else stringResource(Res.string.board_gameOver),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.colorScheme.onPrimaryFixed
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(Res.string.board_cardsLeft, state.cards.size),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryFixed
            )
            if (state.hasGameEnded) {
                Spacer(Modifier.height(16.dp))
                val size = ButtonDefaults.MediumContainerHeight
                Button(
                    modifier = Modifier
                        .heightIn(size)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .bounceClickEffect(),
                    contentPadding = ButtonDefaults.contentPaddingFor(size),
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                        onAction(BoardAction.OnRestartClicked)
                    },
                ) {
                    Icon(
                        modifier = Modifier.size(ButtonDefaults.iconSizeFor(size)),
                        imageVector = Icons.Rounded.RestartAlt,
                        contentDescription = null
                    )
                    Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))
                    Text(stringResource(Res.string.board_buttonNewGame), style = ButtonDefaults.textStyleFor(size))
                }
                Spacer(Modifier.height(8.dp))
                OutlinedButton(
                    modifier = Modifier
                        .heightIn(size)
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally)
                        .bounceClickEffect(),
                    contentPadding = ButtonDefaults.contentPaddingFor(size),
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                        onAction(BoardAction.OnExitClicked)
                    },
                ) {
                    Icon(
                        modifier = Modifier.size(ButtonDefaults.iconSizeFor(size)),
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null
                    )
                    Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))
                    Text(stringResource(Res.string.board_buttonExitGame), style = ButtonDefaults.textStyleFor(size))
                }
            }
        }
    }
}