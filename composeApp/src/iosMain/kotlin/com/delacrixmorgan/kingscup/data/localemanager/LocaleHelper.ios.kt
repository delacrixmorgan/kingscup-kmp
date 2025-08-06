package com.delacrixmorgan.kingscup.data.localemanager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference
import platform.Foundation.NSLocale
import platform.Foundation.NSURL
import platform.Foundation.preferredLanguages
import platform.UIKit.UIApplication
import platform.UIKit.UIApplicationOpenSettingsURLString

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class LocaleHelper {
    // Not Available
    actual fun setLanguage(languageCode: String) = Unit

    actual fun getSystemLanguage(): LocalePreference {
        val preferred = NSLocale.preferredLanguages.firstOrNull() as? String ?: return LocalePreference.Default
        val code = preferred.substringBefore("-")
        return LocalePreference.entries.firstOrNull { it.code == code } ?: LocalePreference.Default
    }

    actual fun openAppSettings() {
        val url = NSURL(string = UIApplicationOpenSettingsURLString)
        if (UIApplication.sharedApplication.canOpenURL(url)) {
            UIApplication.sharedApplication.openURL(url, options = emptyMap<Any?, Any>(), completionHandler = null)
        }
    }
}

@Composable
actual fun rememberLocaleHelper(): LocaleHelper {
    return remember { LocaleHelper() }
}