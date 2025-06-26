package com.delacrixmorgan.kingscup.data.localemanager

import androidx.compose.runtime.Composable

expect class LocaleHelper {
    fun setLanguage(languageCode: String)
    fun getSystemLanguage(): String
    fun openAppSettings()
}

@Composable
expect fun rememberLocaleHelper(): LocaleHelper

