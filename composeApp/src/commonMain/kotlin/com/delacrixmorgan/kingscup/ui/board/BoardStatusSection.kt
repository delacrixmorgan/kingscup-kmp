package com.delacrixmorgan.kingscup.ui.board

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.ui.component.JokerList

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ColumnScope.BoardStatusSection(state: BoardUiState, onAction: (BoardAction) -> Unit) {
    Box(Modifier.fillMaxWidth().weight(1F)) {
        if (state.jokerEnabled) {
            JokerList(
                jokers = state.jokers,
                onJokerClicked = { onAction(BoardAction.OnJokerClicked(it)) },
            )
        }
        Column(Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally) {
            Box(Modifier.width(120.dp).aspectRatio(1F).background(MaterialTheme.colorScheme.secondaryContainer, MaterialShapes.Cookie12Sided.toShape()))
            Spacer(Modifier.height(16.dp))
            Text(
                text = if (!state.hasGameEnded) "Let's go!" else "Game over!",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = "${state.cards.size} cards left",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}