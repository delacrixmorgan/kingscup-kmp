package com.delacrixmorgan.kingscup

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.delacrixmorgan.kingscup.core.local.LocalDataStore
import com.delacrixmorgan.kingscup.data.platform.Environment
import com.delacrixmorgan.kingscup.data.platform.Platform
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module
import platform.Foundation.NSBundle
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual val platform: Platform by lazy { Platform.iOS }

actual val environment: Environment by lazy { if (NSBundle.mainBundle.infoDictionary?.get("Configuration")?.toString() == "Debug") Environment.Debug else Environment.Release }

actual val rateUsStoreLink: String by lazy { "https://github.com/delacrixmorgan/kingscup-kmp" }

actual fun platformModule(): Module = module {
    single(named(LocalDataStore.Preferences.name)) { dataStore(LocalDataStore.Preferences.path) }
}

@OptIn(ExperimentalForeignApi::class)
fun dataStore(path: String): DataStore<Preferences> = createDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$path"
    }
)

actual fun getVersionCode(): String {
    return NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleVersion") as? String ?: "Unknown"
}

actual fun getVersionName(): String {
    return NSBundle.mainBundle.objectForInfoDictionaryKey("CFBundleShortVersionString") as? String ?: "Unknown"
}