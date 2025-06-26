package com.delacrixmorgan.kingscup.data.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.delacrixmorgan.kingscup.data.preferences.model.LocalePreference
import com.delacrixmorgan.kingscup.data.preferences.model.SkinPreference
import com.delacrixmorgan.kingscup.data.preferences.model.ThemePreference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.koin.core.component.KoinComponent

class PreferencesRepository(
    private val dataStore: DataStore<Preferences>
) : KoinComponent {
    companion object {
        private val KEY_LOCALE = stringPreferencesKey("GoqLZdFnasfFBnsgfQfm")
        private val KEY_SKIN = stringPreferencesKey("BBiGXoLNpdXefpYqHRQq")
        private val KEY_THEME = stringPreferencesKey("hzYuGtNdgwJTBrCPspAP")
        private val KEY_JOKER_ENABLED = booleanPreferencesKey("ffhrUNfmCXujeNiwVrPH")
    }

    val localeFlow: Flow<LocalePreference>
        get() = dataStore.data
            .map { LocalePreference.valueOf(it[KEY_LOCALE] ?: LocalePreference.Default.name) }

    suspend fun saveLocale(value: LocalePreference) {
        dataStore.edit { it[KEY_LOCALE] = value.name }
    }

    val skinFlow: Flow<SkinPreference>
        get() = dataStore.data
            .map { SkinPreference.valueOf(it[KEY_SKIN] ?: SkinPreference.Default.name) }

    suspend fun saveSkin(value: SkinPreference) {
        dataStore.edit { it[KEY_SKIN] = value.name }
    }

    val themeFlow: Flow<ThemePreference>
        get() = dataStore.data
            .map { ThemePreference.valueOf(it[KEY_THEME] ?: ThemePreference.Default.name) }

    suspend fun saveTheme(value: ThemePreference) {
        dataStore.edit { it[KEY_THEME] = value.name }
    }

    val jokerEnabledFlow: Flow<Boolean>
        get() = dataStore.data
            .map { it[KEY_JOKER_ENABLED] ?: false }

    suspend fun saveJokerEnabled(value: Boolean) {
        dataStore.edit { it[KEY_JOKER_ENABLED] = value }
    }

    suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}