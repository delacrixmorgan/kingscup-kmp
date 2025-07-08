package com.delacrixmorgan.kingscup.data.localemanager

import androidx.compose.runtime.Composable
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class LocaleHelper {
    fun setLanguage(languageCode: String)
    fun getSystemLanguage(): LocalePreference
    fun openAppSettings()
}

@Composable
expect fun rememberLocaleHelper(): LocaleHelper

