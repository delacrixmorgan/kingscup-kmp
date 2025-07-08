package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialShapes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.toShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.delacrixmorgan.kingscup.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CrownList(
    counter: Int = 0,
    maxItem: Int = 4,
) {
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        repeat(maxItem - counter) {
            CrownItem(filled = true)
        }
        repeat(counter) {
            CrownItem(filled = false)
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
private fun CrownItem(filled: Boolean) {
    Box(
        Modifier
            .width(48.dp)
            .aspectRatio(1F)
            .then(
                if (filled) {
                    Modifier.background(MaterialTheme.colorScheme.tertiaryContainer, MaterialShapes.Triangle.toShape())
                } else {
                    Modifier.dashedBorder(MaterialTheme.colorScheme.tertiaryContainer, MaterialShapes.Triangle.toShape())
                }
            )
    )
}

@Preview
@Composable
private fun Preview() {
    AppTheme {
        CrownList(counter = 2)
    }
}