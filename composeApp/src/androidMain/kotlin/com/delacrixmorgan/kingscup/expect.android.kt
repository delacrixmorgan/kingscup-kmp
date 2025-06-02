package com.delacrixmorgan.kingscup

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.delacrixmorgan.kingscup.core.local.LocalDataStore
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

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