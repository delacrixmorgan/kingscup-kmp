package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import kingscup.composeapp.generated.resources.Res

@Composable
fun AnimatedEmoji(emoji: String) {
    val lottieFile = when (emoji) {
        "❓" -> "question"
        "\uD83D\uDC0D" -> "snake_eyes"
        "\uD83C\uDF7B" -> "mate"
        "☝\uD83C\uDFFB" -> "heaven"
        "\uD83D\uDC67" -> "chicks"
        "\uD83D\uDC4D" -> "thumbs_up"
        "\uD83D\uDC4A" -> "fist"
        "\uD83D\uDCA6" -> "droplet"
        "\uD83D\uDD70\uFE0F" -> "clock"
        "\uD83E\uDD21" -> "clown"
        "\uD83D\uDE31" -> "screaming"
        "\uD83D\uDC48\uD83C\uDFFB" -> "point_left"
        "\uD83E\uDD74" -> "woozy"
        "\uD83E\uDD2C" -> "cursing"
        "\uD83D\uDCF7" -> "camera"
        "\uD83E\uDD23" -> "rofl"
        "✋\uD83C\uDFFB" -> "raised_hand"
        else -> null
    }
    if (!lottieFile.isNullOrBlank()) {
        val composition by rememberLottieComposition {
            LottieCompositionSpec.JsonString(
                Res.readBytes("files/$lottieFile.json").decodeToString()
            )
        }
        Image(
            painter = rememberLottiePainter(
                composition = composition,
                iterations = Compottie.IterateForever
            ),
            contentDescription = "Lottie animation"
        )
    } else {
        Text(text = emoji, style = MaterialTheme.typography.displayLarge)
    }
}