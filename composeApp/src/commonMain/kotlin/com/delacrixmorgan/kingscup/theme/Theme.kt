package com.delacrixmorgan.kingscup.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.theme.color.BoraThemPreference
import com.delacrixmorgan.kingscup.theme.color.ColorPreference
import com.delacrixmorgan.kingscup.theme.color.EmeraldColorPreference
import com.delacrixmorgan.kingscup.theme.color.MadderColorPreference
import com.delacrixmorgan.kingscup.theme.color.SandColorPreference

@Composable
fun AppTheme(
    colorPreference: ColorPreference = listOf(SandColorPreference, BoraThemPreference, EmeraldColorPreference, MadderColorPreference).random(),
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