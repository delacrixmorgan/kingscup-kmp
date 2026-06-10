package com.delacrixmorgan.kingscup.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.delacrixmorgan.kingscup.theme.AppTheme
import com.delacrixmorgan.kingscup.ui.component.AnimatedEmoji
import androidx.compose.ui.tooling.preview.Preview
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.board_pauseDialogDescription
import kingscup.composeapp.generated.resources.board_pauseDialogTitle
import kingscup.composeapp.generated.resources.ic_close
import kingscup.composeapp.generated.resources.ic_restart_alt
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BoardPauseDialog(
    onAction: (BoardAction) -> Unit
) {
    Dialog(
        onDismissRequest = { onAction(BoardAction.OnPauseBottomSheetDismissed) },
        content = {
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceContainerHigh, shape = RoundedCornerShape(24.dp))
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedEmoji("\uD83D\uDE44")
                Spacer(Modifier.height(16.dp))
                Text(
                    stringResource(Res.string.board_pauseDialogTitle),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    stringResource(Res.string.board_pauseDialogDescription),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedButton(
                        modifier = Modifier.weight(1F),
                        onClick = { onAction(BoardAction.OnRestartClicked) }
                    ) {
                        Icon(painter = painterResource(Res.drawable.ic_restart_alt), contentDescription = null)
                    }
                    FilledIconButton(
                        modifier = Modifier.weight(1F),
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MaterialTheme.colorScheme.error,
                            contentColor = MaterialTheme.colorScheme.onError
                        ),
                        onClick = { onAction(BoardAction.OnExitClicked) }
                    ) {
                        Icon(painter = painterResource(Res.drawable.ic_close), contentDescription = null)
                    }
                }
            }
        }
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        BoardPauseDialog(onAction = {})
    }
}