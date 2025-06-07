package com.delacrixmorgan.kingscup.data.preferences.model

import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.theme.color.SkinPalette

enum class SkinPreference(val palette: SkinPalette) {
    Bora(SkinPalette.Bora),
    Emerald(SkinPalette.Emerald),
    Madder(SkinPalette.Madder),
    Sand(SkinPalette.Sand);

    companion object {
        val Default = Bora
    }

    @Composable
    fun getColorScheme(
        themePreference: ThemePreference,
        isSystemDark: Boolean
    ): ColorScheme = when (themePreference) {
        ThemePreference.System -> if (isSystemDark) palette.dark else palette.light
        ThemePreference.Light -> palette.light
        ThemePreference.Dark -> palette.dark
    }
}