package com.delacrixmorgan.kingscup.ui.style.color

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun ColorScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Subheader("Primary")
        ListItem(
            backgroundColorName = "primary",
            textColorName = "onPrimary",
            backgroundColor = MaterialTheme.colorScheme.primary,
            textColor = MaterialTheme.colorScheme.onPrimary
        )
        ListItem(
            backgroundColorName = "primaryContainer",
            textColorName = "onPrimaryContainer",
            backgroundColor = MaterialTheme.colorScheme.primaryContainer,
            textColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
        Subheader("Secondary")
        ListItem(
            backgroundColorName = "secondary",
            textColorName = "onSecondary",
            backgroundColor = MaterialTheme.colorScheme.secondary,
            textColor = MaterialTheme.colorScheme.onSecondary
        )
        ListItem(
            backgroundColorName = "secondaryContainer",
            textColorName = "onSecondaryContainer",
            backgroundColor = MaterialTheme.colorScheme.secondaryContainer,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Subheader("Tertiary")
        ListItem(
            backgroundColorName = "tertiary",
            textColorName = "onTertiary",
            backgroundColor = MaterialTheme.colorScheme.tertiary,
            textColor = MaterialTheme.colorScheme.onTertiary
        )
        ListItem(
            backgroundColorName = "tertiaryContainer",
            textColorName = "onTertiaryContainer",
            backgroundColor = MaterialTheme.colorScheme.tertiaryContainer,
            textColor = MaterialTheme.colorScheme.onTertiaryContainer
        )
        Subheader("Error")
        ListItem(
            backgroundColorName = "error",
            textColorName = "onError",
            backgroundColor = MaterialTheme.colorScheme.error,
            textColor = MaterialTheme.colorScheme.onError
        )
        ListItem(
            backgroundColorName = "errorContainer",
            textColorName = "onErrorContainer",
            backgroundColor = MaterialTheme.colorScheme.errorContainer,
            textColor = MaterialTheme.colorScheme.onErrorContainer
        )
        Subheader("Background")
        ListItem(
            backgroundColorName = "background",
            textColorName = "onBackground",
            backgroundColor = MaterialTheme.colorScheme.background,
            textColor = MaterialTheme.colorScheme.onBackground
        )
        Subheader("Surface")
        ListItem(
            backgroundColorName = "surface",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.surface,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ListItem(
            backgroundColorName = "surfaceVariant",
            textColorName = "onSurfaceVariant",
            backgroundColor = MaterialTheme.colorScheme.surfaceVariant,
            textColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
        ListItem(
            backgroundColorName = "surfaceDim",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.surfaceDim,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ListItem(
            backgroundColorName = "surfaceBright",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.surfaceBright,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ListItem(
            backgroundColorName = "surfaceContainerLowest",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ListItem(
            backgroundColorName = "surfaceContainerLow",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerLow,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ListItem(
            backgroundColorName = "surfaceContainer",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.surfaceContainer,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ListItem(
            backgroundColorName = "surfaceContainerHigh",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerHigh,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ListItem(
            backgroundColorName = "surfaceContainerHighest",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        Subheader("Inverse")
        ListItem(
            backgroundColorName = "inverseSurface",
            textColorName = "inverseOnSurface",
            backgroundColor = MaterialTheme.colorScheme.inverseSurface,
            textColor = MaterialTheme.colorScheme.inverseOnSurface
        )
        ListItem(
            backgroundColorName = "inversePrimary",
            textColorName = "onSurface",
            backgroundColor = MaterialTheme.colorScheme.inversePrimary,
            textColor = MaterialTheme.colorScheme.onSurface
        )
        ListItem(
            backgroundColorName = "background",
            textColorName = "outline",
            backgroundColor = MaterialTheme.colorScheme.outline,
            textColor = MaterialTheme.colorScheme.background
        )
        ListItem(
            backgroundColorName = "background",
            textColorName = "outlineVariant",
            backgroundColor = MaterialTheme.colorScheme.outlineVariant,
            textColor = MaterialTheme.colorScheme.background
        )
        ListItem(
            backgroundColorName = "scrim",
            textColorName = "inverseOnSurface",
            backgroundColor = MaterialTheme.colorScheme.scrim,
            textColor = MaterialTheme.colorScheme.inverseOnSurface
        )
    }
}

@Composable
private fun Subheader(
    text: String,
) {
    Text(text, color = MaterialTheme.colorScheme.onSurface)
}

@Composable
private fun ListItem(
    backgroundColorName: String,
    textColorName: String,
    backgroundColor: Color,
    textColor: Color
) {
    ListItem(
        modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(16.dp)),
        colors = ListItemDefaults.colors().copy(containerColor = backgroundColor),
        headlineContent = { Text(text = textColorName, color = textColor) },
        supportingContent = { Text(text = backgroundColorName, color = textColor) }
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        ColorScreen()
    }
}