package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.gestures.ScrollableDefaults
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.data.model.Card

@Composable
fun BouncyLazyRow(
    modifier: Modifier = Modifier,
    state: LazyListState,
    cards: List<Card>,
    onItemClicked: (String) -> Unit,
    initialVisible: Boolean = false,
    haptic: HapticFeedback = LocalHapticFeedback.current,
) {
    val cardWidth = 150.dp
    var visible by remember { mutableStateOf(initialVisible) }
    LaunchedEffect(Unit) { visible = true }

    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(
            initialOffsetX = { it },
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioMediumBouncy,
                stiffness = Spring.StiffnessLow
            )
        ),
    ) {
        LazyRow(
            modifier = modifier
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
            items(cards) { card ->
                var isPressed by remember { mutableStateOf(false) }
                val animatedElevation by animateDpAsState(
                    targetValue = if (isPressed) 8.dp else 2.dp,
                    label = "CardElevation"
                )

                val animatedScale by animateFloatAsState(
                    targetValue = if (isPressed) 1.05f else 1f,
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
                                    onItemClicked(card.rule.id)
                                }
                            )
                        },
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = animatedElevation),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onPrimaryContainer)
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(card.rule.id, color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
            }
        }
    }
}