package com.delacrixmorgan.kingscup.data.localemanager

import android.app.LocaleManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.LocaleList
import android.provider.Settings
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.os.ConfigurationCompat
import androidx.core.os.LocaleListCompat
import androidx.core.text.util.LocalePreferences
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference

/**
 * https://blog.kotlin-academy.com/localization-in-jetpack-compose-71b7f7233243
 */
@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class LocaleHelper(private val context: Context) {
    actual fun setLanguage(languageCode: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            context.getSystemService(LocaleManager::class.java).applicationLocales = LocaleList.forLanguageTags(languageCode)
        } else {
            AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(languageCode))
        }
    }

    actual fun getSystemLanguage(): LocalePreference {
        val locale = ConfigurationCompat.getLocales(context.resources.configuration)[0]
        return LocalePreference.entries.firstOrNull { it.code == locale?.language } ?: LocalePreference.Default
    }

    // API 31 (Android 13 - Tiramisu) >=
    actual fun openAppSettings() {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
            data = Uri.fromParts("package", context.packageName, null)
        }
        context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK))
    }
}

@Composable
actual fun rememberLocaleHelper(): LocaleHelper {
    val context = LocalContext.current
    return remember { LocaleHelper(context) }
}