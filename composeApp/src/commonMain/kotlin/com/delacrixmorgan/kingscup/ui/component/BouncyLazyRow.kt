package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedback
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.data.card.model.Card
import com.delacrixmorgan.kingscup.data.platform.Environment
import com.delacrixmorgan.kingscup.environment
import com.delacrixmorgan.kingscup.theme.AppTheme
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.app_name
import kingscup.composeapp.generated.resources.img_logo
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun BouncyLazyRow(
    modifier: Modifier = Modifier,
    state: LazyListState,
    cards: List<Card>,
    animateBounce: Boolean = false,
    onItemClicked: (Int) -> Unit,
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    val cardWidth = 180.dp
    val offsetX = remember { Animatable(if (animateBounce) 0F else 1_000F) }

    LaunchedEffect(animateBounce) {
        if (!animateBounce) {
            delay(100)
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
        }
    }
    LazyRow(
        modifier = modifier
            .offset { IntOffset(offsetX.value.toInt(), 0) }
            .layout { measurable, constraints ->
                val width = constraints.maxWidth + cardWidth.roundToPx()
                val forcedConstraints = constraints.copy(minWidth = width, maxWidth = width)
                val placeable = measurable.measure(forcedConstraints)
                layout(placeable.width, placeable.height) {
                    val xPos = (placeable.width - constraints.maxWidth) / 2
                    placeable.placeRelative(xPos, 0)
                }
            },
        state = state,
        contentPadding = PaddingValues(start = 16.dp, end = cardWidth + 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        flingBehavior = ScrollableDefaults.flingBehavior(),
    ) {
        itemsIndexed(items = cards, key = { _, card -> card.uuid }) { index, card ->
            var isPressed by remember { mutableStateOf(false) }
            val animatedElevation by animateDpAsState(
                targetValue = if (isPressed) 8.dp else 2.dp,
                label = "CardElevation"
            )

            val animatedScale by animateFloatAsState(
                targetValue = if (isPressed) 1.05F else 1F,
                label = "CardScale"
            )
            Card(
                modifier = Modifier
                    .width(cardWidth)
                    .aspectRatio(63F / 88F)
                    .animateItem()
                    .graphicsLayer {
                        scaleX = animatedScale
                        scaleY = animatedScale
                        shadowElevation = animatedElevation.toPx()
                        shape = RoundedCornerShape(12.dp)
                        clip = false
                    }
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onPress = {
                                isPressed = true
                                try {
                                    this.awaitRelease()
                                } finally {
                                    isPressed = false
                                }
                            },
                            onTap = {
                                haptic.performHapticFeedback(HapticFeedbackType.ContextClick)
                                onItemClicked(index)
                            }
                        )
                    },
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                PlayableCard(card.rule.id)
            }
        }
    }
}

@Composable
private fun PlayableCard(id: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (environment) {
            Environment.Debug -> {
                Text(id, color = MaterialTheme.colorScheme.onPrimary)
            }
            Environment.Release -> {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Image(
                        modifier = Modifier.widthIn(max = 36.dp),
                        painter = painterResource(Res.drawable.img_logo),
                        contentDescription = null
                    )
                    Text(
                        stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.headlineSmall,
                        color = MaterialTheme.colorScheme.onPrimary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        PlayableCard(id = "King")
    }
}