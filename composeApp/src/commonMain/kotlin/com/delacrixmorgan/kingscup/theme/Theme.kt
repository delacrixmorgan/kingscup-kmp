package com.delacrixmorgan.kingscup.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference

@Composable
fun AppTheme(
    skin: SkinPreference = SkinPreference.Default,
    theme: ThemePreference = ThemePreference.Default,
    isSystemDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = skin.getColorScheme(theme, isSystemDark),
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}