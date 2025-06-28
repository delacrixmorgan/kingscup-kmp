package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import io.github.vinceglb.confettikit.compose.ConfettiKit
import io.github.vinceglb.confettikit.core.Angle
import io.github.vinceglb.confettikit.core.Party
import io.github.vinceglb.confettikit.core.Position
import io.github.vinceglb.confettikit.core.Spread
import io.github.vinceglb.confettikit.core.emitter.Emitter
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

@Composable
fun Confetti() {
    val colors = listOf(
        MaterialTheme.colorScheme.primaryContainer.toArgb(),
        MaterialTheme.colorScheme.secondaryContainer.toArgb(),
        MaterialTheme.colorScheme.tertiaryContainer.toArgb(),
    )
    ConfettiKit(
        modifier = Modifier.fillMaxSize(),
        parties = listOf(explode(colors), parade(colors), rain(colors)).random()
    )
}

private fun explode(colors: List<Int>): List<Party> {
    return listOf(
        Party(
            delay = 500,
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = colors,
            emitter = Emitter(duration = 100.milliseconds).max(100),
        )
    )
}

private fun parade(colors: List<Int>): List<Party> {
    val yPosition = 0.2
    val party = Party(
        delay = 500,
        speed = 10f,
        maxSpeed = 30f,
        damping = 0.9f,
        angle = Angle.RIGHT - 45,
        spread = Spread.SMALL,
        colors = colors,
        emitter = Emitter(duration = 5.seconds).perSecond(30),
        position = Position.Relative(0.0, yPosition)
    )

    return listOf(
        party,
        party.copy(
            angle = party.angle - 90,
            position = Position.Relative(1.0, yPosition)
        ),
    )
}

private fun rain(colors: List<Int>): List<Party> {
    return listOf(
        Party(
            speed = 0f,
            maxSpeed = 15f,
            damping = 0.9f,
            angle = Angle.BOTTOM,
            spread = Spread.ROUND,
            colors = colors,
            emitter = Emitter(duration = 3.5.seconds).perSecond(100),
            position = Position.Relative(0.0, 0.0).between(Position.Relative(1.0, 0.0))
        )
    )
}