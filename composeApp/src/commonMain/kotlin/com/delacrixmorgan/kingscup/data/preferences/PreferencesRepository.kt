package com.delacrixmorgan.kingscup.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

internal class PreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : KoinComponent {
    companion object {
        const val KEY_SKIN = "hzYuGtNdgwJTBrCPspAP"
        const val KEY_THEME = "hzYuGtNdgwJTBrCPspAP"
        const val KEY_JOKER_ENABLED = "ffhrUNfmCXujeNiwVrPH"
        const val KEY_LOCALE = "GoqLZdFnasfFBnsgfQfm"
    }

    val skinFlow: Flow<SkinPreference>
        get() = dataStore.data
            .map { SkinPreference.valueOf(it[stringPreferencesKey(KEY_SKIN)] ?: SkinPreference.Default.name) }

    suspend fun saveSkin(value: SkinPreference) {
        dataStore.edit { it[stringPreferencesKey(KEY_SKIN)] = value.name }
    }

    val themeFlow: Flow<ThemePreference>
        get() = dataStore.data
            .map { ThemePreference.valueOf(it[stringPreferencesKey(KEY_THEME)] ?: ThemePreference.Default.name) }

    suspend fun saveTheme(value: ThemePreference) {
        dataStore.edit { it[stringPreferencesKey(KEY_THEME)] = value.name }
    }

    val jokerEnabledFlow: Flow<Boolean>
        get() = dataStore.data
            .map { it[booleanPreferencesKey(KEY_JOKER_ENABLED)] ?: false }

    suspend fun saveJokerEnabled(value: Boolean) {
        dataStore.edit { it[booleanPreferencesKey(KEY_JOKER_ENABLED)] = value }
    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}