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

    object Bora : SkinPalette(
        light = BoraColorPreference.lightScheme,
        dark = BoraColorPreference.darkScheme,
    )

    object Emerald : SkinPalette(
        light = EmeraldColorPreference.lightScheme,
        dark = EmeraldColorPreference.darkScheme,
    )

    object Madder : SkinPalette(
        light = MadderColorPreference.lightScheme,
        dark = MadderColorPreference.darkScheme,
    )

    object Sand : SkinPalette(
        light = SandColorPreference.lightScheme,
        dark = SandColorPreference.darkScheme,
    )
}