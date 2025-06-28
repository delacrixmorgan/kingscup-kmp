package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.data.card.model.Joker
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Composable
fun JokerList(
    jokers: List<Card>,
    onJokerClicked: (Card) -> Unit
) {
    Box(Modifier.fillMaxWidth()) {
        Column(
            Modifier.align(Alignment.CenterStart).padding(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            JokerStartItem(Modifier.weight(1F), joker = jokers.getOrNull(0), onJokerClicked = onJokerClicked)
            JokerStartItem(Modifier.weight(1F), joker = jokers.getOrNull(1), onJokerClicked = onJokerClicked)
        }
        Column(
            Modifier.align(Alignment.CenterEnd).padding(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            JokerEndItem(Modifier.weight(1F), joker = jokers.getOrNull(2), onJokerClicked = onJokerClicked)
            JokerEndItem(Modifier.weight(1F), joker = jokers.getOrNull(3), onJokerClicked = onJokerClicked)
        }
    }
}

@Composable
private fun JokerStartItem(
    modifier: Modifier,
    joker: Card? = null,
    onJokerClicked: (Card) -> Unit,
    haptic: HapticFeedback = LocalHapticFeedback.current
) {
    val shape = RoundedCornerShape(
        topStart = 0.dp,
        bottomStart = 0.dp,
        topEnd = 12.dp,
        bottomEnd = 12.dp
    )
    Box(
        modifier
            .width(52.dp)
            .then(
                if (joker != null) {
                    Modifier
                        .bounceClickEffect()
                        .background(MaterialTheme.colorScheme.secondaryContainer, shape)
                        .clip(shape)
                        .clickable {
                            haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                            onJokerClicked(joker)
                        }
                } else {
                    Modifier
                        .offset((-2).dp)
                        .dashedBorder(MaterialTheme.colorScheme.secondaryContainer, shape)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (joker != null) {
            Text(
                text = stringResource(joker.rule.emoji),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun JokerEndItem(
    modifier: Modifier,
    joker: Card? = null,
    onJokerClicked: (Card) -> Unit,
    haptic: HapticFeedback = LocalHapticFeedback.current
) {
    val shape = RoundedCornerShape(
        topStart = 12.dp,
        bottomStart = 12.dp,
        topEnd = 0.dp,
        bottomEnd = 0.dp
    )
    Box(
        modifier
            .width(52.dp)
            .then(
                if (joker != null) {
                    Modifier
                        .bounceClickEffect()
                        .background(MaterialTheme.colorScheme.secondaryContainer, shape)
                        .clip(shape)
                        .clickable {
                            haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                            onJokerClicked(joker)
                        }
                } else {
                    Modifier
                        .offset(2.dp)
                        .dashedBorder(MaterialTheme.colorScheme.secondaryContainer, shape)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (joker != null) {
            Text(
                text = stringResource(joker.rule.emoji),
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

@OptIn(ExperimentalUuidApi::class)
@Preview
@Composable
private fun Preview() {
    AppTheme {
        JokerList(
            jokers = listOf(
                Joker.IWillTellYouWhat,
                Joker.Toilet,
            ).map {
                Card(uuid = Uuid.random().toString(), suit = Card.SuitType.Joker, rank = Card.RankType.Joker, rule = it)
            },
            onJokerClicked = {}
        )
    }
}