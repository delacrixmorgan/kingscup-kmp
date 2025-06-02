package com.delacrixmorgan.kingscup.core.local

enum class LocalDataStore(private val url: String) {
    Preferences("kingscup.preferences_data_store");

    val path: String get() = "$url.preferences_pb"
}