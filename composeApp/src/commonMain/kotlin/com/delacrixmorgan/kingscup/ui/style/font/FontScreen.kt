package com.delacrixmorgan.kingscup.ui.style.font

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun FontScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp)
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "displayLarge",
            style = MaterialTheme.typography.displayLarge
        )
        Text(
            text = "displayLargeEmphasized",
            style = MaterialTheme.typography.displayLargeEmphasized
        )
        Text(
            text = "displayMedium",
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            text = "displayMediumEmphasized",
            style = MaterialTheme.typography.displayMediumEmphasized
        )
        Text(
            text = "displaySmall",
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = "displaySmallEmphasized",
            style = MaterialTheme.typography.displaySmallEmphasized
        )
        HorizontalDivider()

        Text(
            text = "headlineLarge",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "headlineLargeEmphasized",
            style = MaterialTheme.typography.headlineLargeEmphasized
        )
        Text(
            text = "headlineMedium",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "headlineMediumEmphasized",
            style = MaterialTheme.typography.headlineMediumEmphasized
        )
        Text(
            text = "headlineSmall",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "headlineSmallEmphasized",
            style = MaterialTheme.typography.headlineSmallEmphasized
        )
        HorizontalDivider()

        Text(
            text = "titleLarge",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = "titleLargeEmphasized",
            style = MaterialTheme.typography.titleLargeEmphasized
        )
        Text(
            text = "titleMedium",
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = "titleMediumEmphasized",
            style = MaterialTheme.typography.titleMediumEmphasized
        )
        Text(
            text = "titleSmall",
            style = MaterialTheme.typography.titleSmall
        )
        Text(
            text = "titleSmallEmphasized",
            style = MaterialTheme.typography.titleSmallEmphasized
        )
        HorizontalDivider()

        Text(
            text = "bodyLarge",
            style = MaterialTheme.typography.bodyLarge
        )
        Text(
            text = "bodyLargeEmphasized",
            style = MaterialTheme.typography.bodyLargeEmphasized
        )
        Text(
            text = "bodyMedium",
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            text = "bodyMediumEmphasized",
            style = MaterialTheme.typography.bodyMediumEmphasized
        )
        Text(
            text = "bodySmall",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "bodySmallEmphasized",
            style = MaterialTheme.typography.bodySmallEmphasized
        )
        HorizontalDivider()

        Text(
            text = "labelLarge",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "labelLargeEmphasized",
            style = MaterialTheme.typography.labelLargeEmphasized
        )
        Text(
            text = "labelMedium",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "labelMediumEmphasized",
            style = MaterialTheme.typography.labelMediumEmphasized
        )
        Text(
            text = "labelSmall",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "labelSmallEmphasized",
            style = MaterialTheme.typography.labelSmallEmphasized
        )
        HorizontalDivider()

        Text(
            text = "labelLarge",
            style = MaterialTheme.typography.labelLarge
        )
        Text(
            text = "labelLargeEmphasized",
            style = MaterialTheme.typography.labelLargeEmphasized
        )
        Text(
            text = "labelMedium",
            style = MaterialTheme.typography.labelMedium
        )
        Text(
            text = "labelMediumEmphasized",
            style = MaterialTheme.typography.labelMediumEmphasized
        )
        Text(
            text = "labelSmall",
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            text = "labelSmallEmphasized",
            style = MaterialTheme.typography.labelSmallEmphasized
        )
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        FontScreen()
    }
}