package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import kingscup.composeapp.generated.resources.Res

@Composable
fun AnimatedEmoji(emoji: String) {
    val lottieFile = when (emoji) {
        "\uD83C\uDF1F" -> "glowing_star" // 🌟
        "❓" -> "question" // ❓
        "\uD83E\uDD14" -> "thinking_face" // 🤔
        "\uD83D\uDD2E" -> "crystal_ball" // 🔮
        "\uD83D\uDC0D" -> "snake" // 🐍
        "\uD83E\uDEF6" -> "heart_hands" // 🫶
        "☝\uD83C\uDFFB" -> "point_up" // ☝️
        "\uD83D\uDC83" -> "dancer_woman" // 💃
        "\uD83E\uDD20" -> "cowboy" // 🤠
        "\uD83D\uDC4D" -> "thumbs_up" // 👍
        "\uD83D\uDC4A" -> "fist" // 👊
        "\uD83E\uDD1D" -> "handshake"// 🤝
        "\uD83C\uDF0A" -> "ocean" // 🌊
        "⏰" -> "clock" // ⏰
        "\uD83E\uDEF3" -> "palm_down" // 🫳
        "\uD83C\uDF7B" -> "clinking_beer_mugs" // 🍻
        "\uD83E\uDD21" -> "clown" // 🤡
        "\uD83D\uDE08" -> "imp_smile" // 😈
        "\uD83D\uDE31" -> "screaming" // 😱
        "\uD83E\uDEF5" -> "pointing" // 🫵
        "\uD83E\uDD96" -> "t_rex" // 🦖
        "\uD83E\uDD13" -> "nerd_face" // 🤓
        "\uD83E\uDD74" -> "woozy" // 🥴
        "\uD83E\uDD33" -> "selfie" // 🤳
        "\uD83E\uDD2C" -> "cursing" // 🤬
        "\uD83D\uDCF7" -> "camera" // 📷
        "\uD83E\uDD23" -> "rofl" // 🤣
        "✋\uD83C\uDFFB" -> "raised_hand" // ✋
        "☣\uFE0F" -> "biohazard" // ☣️
        else -> null
    }
    if (!lottieFile.isNullOrBlank()) {
        val composition by rememberLottieComposition {
            LottieCompositionSpec.JsonString(
                Res.readBytes("files/$lottieFile.json").decodeToString()
            )
        }
        Image(
            modifier = Modifier.size(128.dp),
            painter = rememberLottiePainter(
                composition = composition,
                iterations = Compottie.IterateForever
            ),
            contentDescription = "Lottie animation"
        )
    } else {
        Text(
            modifier = Modifier.size(128.dp).wrapContentHeight(align = Alignment.CenterVertically),
            text = emoji,
            fontSize = 76.sp,
            textAlign = TextAlign.Center
        )
    }
}