package com.delacrixmorgan.kingscup.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kingscup.composeapp.generated.resources.Res
import kingscup.composeapp.generated.resources.lato_bold
import kingscup.composeapp.generated.resources.lato_regular
import kingscup.composeapp.generated.resources.league_spartan_semibold
import org.jetbrains.compose.resources.Font

val displayFontFamily: FontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.league_spartan_semibold, weight = FontWeight.SemiBold),
    )

val bodyFontFamily: FontFamily
    @Composable get() = FontFamily(
        Font(Res.font.lato_bold, weight = FontWeight.Bold),
        Font(Res.font.lato_regular, weight = FontWeight.Normal),
    )

val AppTypography: Typography
    @Composable get() {
        val baseline = Typography()
        val displayFontFamily = displayFontFamily
        val bodyFontFamily = bodyFontFamily

        return Typography(
            displayLarge = baseline.displayLarge.copy(fontFamily = displayFontFamily),
            displayMedium = baseline.displayMedium.copy(fontFamily = displayFontFamily),
            displaySmall = baseline.displaySmall.copy(fontFamily = displayFontFamily),
            headlineLarge = baseline.headlineLarge.copy(fontFamily = displayFontFamily),
            headlineMedium = baseline.headlineMedium.copy(fontFamily = displayFontFamily),
            headlineSmall = baseline.headlineSmall.copy(fontFamily = displayFontFamily),
            titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily),
            titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily),
            titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily),
            bodyLarge = baseline.bodyLarge.copy(fontFamily = bodyFontFamily),
            bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily),
            bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily),
            labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily, fontWeight = FontWeight.Bold),
            labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily),
            labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily),
        )
    }

val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp)
)