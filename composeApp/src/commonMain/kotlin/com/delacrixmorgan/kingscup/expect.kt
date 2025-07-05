package com.delacrixmorgan.kingscup

import androidx.compose.runtime.Composable
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import com.delacrixmorgan.kingscup.data.platform.Environment
import com.delacrixmorgan.kingscup.data.platform.Platform
import okio.Path.Companion.toPath
import org.koin.core.module.Module

expect val platform: Platform

expect val environment: Environment

expect val rateUsStoreLink: String

expect fun platformModule(): Module

fun createDataStore(
    producePath: () -> String,
): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    corruptionHandler = null,
    migrations = emptyList(),
    produceFile = { producePath().toPath() },
)

expect fun getVersionCode(): String

expect fun getVersionName(): String

@Composable
expect fun LightStatusBar(enable: Boolean)