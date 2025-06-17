package com.delacrixmorgan.kingscup.data.localemanager

import androidx.compose.runtime.Composable

expect class UrlLauncher {
    fun openAppSettings()
}

@Composable
expect fun rememberUrlLauncher(): UrlLauncher

