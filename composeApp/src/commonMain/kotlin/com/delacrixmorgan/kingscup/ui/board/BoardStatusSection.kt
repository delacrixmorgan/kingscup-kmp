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
                text = if (!state.hasGameEnded) "Let's go!" else "Game over!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimaryFixed
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${state.cards.size} cards left",
                style = MaterialTheme.typography.bodyLarge,
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
                        Icons.Rounded.RestartAlt,
                        contentDescription = "New game",
                        modifier = Modifier.size(ButtonDefaults.iconSizeFor(size))
                    )
                    Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))
                    Text("New Game", style = ButtonDefaults.textStyleFor(size))
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
                        imageVector = Icons.Rounded.Close,
                        contentDescription = "Exit game",
                        modifier = Modifier.size(ButtonDefaults.iconSizeFor(size))
                    )
                    Spacer(Modifier.size(ButtonDefaults.iconSpacingFor(size)))
                    Text("Exit Game", style = ButtonDefaults.textStyleFor(size))
                }
            }
        }
    }
}