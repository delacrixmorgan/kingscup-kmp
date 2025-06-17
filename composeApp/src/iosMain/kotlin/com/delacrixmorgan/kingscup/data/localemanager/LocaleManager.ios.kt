package com.delacrixmorgan.kingscup.data.localemanager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode

class IosAppLocaleManager : AppLocaleManager {
    override fun getLocale(): String {
        val nsLocale = NSLocale.currentLocale.languageCode
        return nsLocale
    }
}

@Composable
actual fun rememberAppLocale(): LocalePreference {
    val nsLocale = IosAppLocaleManager().getLocale()
    return remember(nsLocale) {
        LocalePreference.entries.firstOrNull { it.code == nsLocale } ?: LocalePreference.Default
    }
}