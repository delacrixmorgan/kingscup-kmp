package com.delacrixmorgan.kingscup.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.ui.theme.color.ColorPreference
import com.delacrixmorgan.kingscup.ui.theme.color.SandColorPreference

@Composable
fun AppTheme(
    colorPreference: ColorPreference = SandColorPreference,
    content: @Composable () -> Unit
) {
    val colorScheme = if (!isSystemInDarkTheme()) {
        colorPreference.lightScheme
    } else {
        colorPreference.darkScheme
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        shapes = AppShapes,
        content = content
    )
}