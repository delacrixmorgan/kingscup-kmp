package com.delacrixmorgan.kingscup.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.delacrixmorgan.kingscup.nav.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(modifier: Modifier = Modifier) {
    AppNavHost()
}