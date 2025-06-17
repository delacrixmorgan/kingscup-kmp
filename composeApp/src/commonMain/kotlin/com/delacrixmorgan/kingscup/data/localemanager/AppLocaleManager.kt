package com.delacrixmorgan.kingscup.data.localemanager

import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference

interface AppLocaleManager {
    fun getLocale(): String
}

@Composable
expect fun rememberAppLocale(): LocalePreference