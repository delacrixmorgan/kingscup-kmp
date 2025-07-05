package com.delacrixmorgan.kingscup.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.delacrixmorgan.kingscup.LightStatusBar

@Composable
fun BoxBackground(content: @Composable BoxScope.() -> Unit) {
    LightStatusBar(enable = false)
    Box(Modifier.fillMaxSize()) {
        Column(Modifier.fillMaxSize()) {
            Box(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.primaryContainer).weight(1.36F))
            Box(Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.secondaryContainer).weight(1F))
        }
        content()
    }
}