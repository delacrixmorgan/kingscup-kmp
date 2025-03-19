package com.delacrixmorgan.kingscup.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.data.model.ThemePreference

@Composable
fun AppTheme(
    theme: ThemePreference = ThemePreference.Default,
    content: @Composable () -> Unit
) {
//    val colorScheme = when (theme) {
//        ThemePreference.System -> if (isSystemInDarkTheme()) darkScheme else lightScheme
//        ThemePreference.Light -> lightScheme
//        ThemePreference.Dark -> darkScheme
//    }

    MaterialTheme

//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = AppTypography,
//        shapes = AppShapes,
//        content = content
//    )
}