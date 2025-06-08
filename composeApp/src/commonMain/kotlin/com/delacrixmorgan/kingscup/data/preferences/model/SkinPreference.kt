package com.delacrixmorgan.kingscup.data.preferences.model

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.delacrixmorgan.kingscup.theme.color.BoraColorPreference
import com.delacrixmorgan.kingscup.theme.color.ClassicColorPreference
import com.delacrixmorgan.kingscup.theme.color.EmeraldColorPreference
import com.delacrixmorgan.kingscup.theme.color.SandColorPreference
import com.delacrixmorgan.kingscup.theme.color.SkinPalette

enum class SkinPreference(val palette: SkinPalette) {
    Classic(SkinPalette.Classic),
    Bora(SkinPalette.Bora),
    Emerald(SkinPalette.Emerald),
    Sand(SkinPalette.Sand);

    companion object {
        val Default = Classic
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

    @Composable
    fun getCardColor(
        isSystemDark: Boolean = isSystemInDarkTheme(),
    ): Color = when (this) {
        Classic -> if (!isSystemDark) ClassicColorPreference.primaryContainerLight else ClassicColorPreference.primaryContainerDark
        Bora -> if (!isSystemDark) BoraColorPreference.primaryContainerLight else BoraColorPreference.primaryContainerDark
        Emerald -> if (!isSystemDark) EmeraldColorPreference.primaryContainerLight else EmeraldColorPreference.primaryContainerDark
        Sand -> if (!isSystemDark) SandColorPreference.primaryContainerLight else SandColorPreference.primaryContainerDark
    }
}