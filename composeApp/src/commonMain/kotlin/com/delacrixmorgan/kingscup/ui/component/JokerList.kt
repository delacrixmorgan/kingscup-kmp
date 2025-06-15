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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun JokerList(
    topStartFilled: Boolean = false,
    bottomStartFilled: Boolean = false,
    topEndFilled: Boolean = false,
    bottomEndFilled: Boolean = false,
) {
    Box(Modifier.fillMaxWidth()) {
        Column(
            Modifier.align(Alignment.CenterStart).padding(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            JokerStartItem(Modifier.weight(1F), filled = topStartFilled)
            JokerStartItem(Modifier.weight(1F), filled = bottomStartFilled)
        }
        Column(
            Modifier.align(Alignment.CenterEnd).padding(vertical = 32.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            JokerEndItem(Modifier.weight(1F), filled = topEndFilled)
            JokerEndItem(Modifier.weight(1F), filled = bottomEndFilled)
        }
    }
}

@Composable
private fun JokerStartItem(
    modifier: Modifier,
    filled: Boolean
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
                if (filled) {
                    Modifier.background(MaterialTheme.colorScheme.secondaryContainer, shape)
                        .clip(shape)
                        .clickable { }
                } else {
                    Modifier
                        .offset((-2).dp)
                        .dashedBorder(MaterialTheme.colorScheme.secondaryContainer, shape)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (filled) {
            Text(
                text = "\uD83C\uDCCF",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun JokerEndItem(
    modifier: Modifier,
    filled: Boolean
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
                if (filled) {
                    Modifier
                        .background(MaterialTheme.colorScheme.secondaryContainer, shape)
                        .clip(shape)
                        .clickable { }
                } else {
                    Modifier
                        .offset(2.dp)
                        .dashedBorder(MaterialTheme.colorScheme.secondaryContainer, shape)
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (filled) {
            Text(
                text = "\uD83C\uDCCF",
                style = MaterialTheme.typography.displayMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        JokerList(
            topStartFilled = true,
            bottomStartFilled = false,
            topEndFilled = true,
            bottomEndFilled = false,
        )
    }
}