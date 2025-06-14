package com.delacrixmorgan.kingscup.theme

import androidx.compose.material3.ListItemColors
import androidx.compose.material3.ListItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun appListItemColors(): ListItemColors = ListItemDefaults.colors().copy(
    containerColor = Color.Transparent
)