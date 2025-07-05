package com.delacrixmorgan.kingscup

import android.content.Context
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.core.view.WindowInsetsControllerCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.delacrixmorgan.kingscup.core.local.LocalDataStore
import com.delacrixmorgan.kingscup.data.platform.Environment
import com.delacrixmorgan.kingscup.data.platform.Platform
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val platform: Platform by lazy { Platform.Android }

actual val environment: Environment by lazy { Environment.Release }
//actual val environment: Environment by lazy { if (BuildConfig.DEBUG) Environment.Debug else Environment.Release }

actual val rateUsStoreLink: String by lazy { "https://play.google.com/store/apps/details?id=com.delacrixmorgan.kingscup.android" }

actual fun platformModule(): Module = module {
    single(named(LocalDataStore.Preferences.name)) { dataStore(get(), LocalDataStore.Preferences.path) }
}

fun dataStore(context: Context, path: String): DataStore<Preferences> = createDataStore(
    producePath = { context.filesDir.resolve(path).absolutePath }
)

actual fun getVersionCode(): String {
    return BuildConfig.VERSION_CODE.toString()
}

actual fun getVersionName(): String {
    return BuildConfig.VERSION_NAME
}