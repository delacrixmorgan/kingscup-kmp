package com.delacrixmorgan.kingscup.data.localemanager

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class UrlLauncher(private val context: Context) {
    actual fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}


@Composable
actual fun rememberUrlLauncher(): UrlLauncher {
    val context = LocalContext.current
    return remember {
        UrlLauncher(context)
    }
}