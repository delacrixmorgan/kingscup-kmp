package com.delacrixmorgan.kingscup.theme.color

import androidx.compose.material3.ColorScheme

sealed class SkinPalette(
    val light: ColorScheme,
    val dark: ColorScheme
) {
    object Classic : SkinPalette(
        light = ClassicColorPreference.lightScheme,
        dark = ClassicColorPreference.darkScheme,
    )
}