package com.delacrixmorgan.kingscup.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.delacrixmorgan.kingscup.data.localemanager.rememberAppLocale
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference
import com.delacrixmorgan.kingscup.nav.AppNavHost
import org.jetbrains.compose.ui.tooling.preview.Preview

val LocalAppLocalization = compositionLocalOf {
    LocalePreference.Chinese
}

@Composable
@Preview
fun App(modifier: Modifier = Modifier) {
    val currentLanguage = rememberAppLocale()
    CompositionLocalProvider(LocalAppLocalization provides currentLanguage) {
        AppNavHost()
    }
}