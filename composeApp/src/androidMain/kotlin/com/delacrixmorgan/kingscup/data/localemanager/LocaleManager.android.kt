package com.delacrixmorgan.kingscup.data.localemanager

import android.app.LocaleManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.getSystemService
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference

class AndroidAppAppLocaleManager(
    private val context: Context,
) : AppLocaleManager {
    private val localManager by lazy { context.getSystemService<LocaleManager>() }

    override fun getLocale(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val locales = localManager?.applicationLocales ?: return "en"
            if (locales.isEmpty) "en" else
                locales[0]?.toLanguageTag()?.split("-")?.firstOrNull() ?: "en"
        } else {
            AppCompatDelegate.getApplicationLocales()
                .toLanguageTags().split("-")
                .firstOrNull() ?: "en"
        }
    }
}

@Composable
actual fun rememberAppLocale(): LocalePreference {
    val context = LocalContext.current
    val locale = AndroidAppAppLocaleManager(context).getLocale()
    return remember(locale) {
        LocalePreference.entries.firstOrNull { it.code == locale } ?: LocalePreference.Default
    }
}