package com.bearminds.passkeep.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface AppPreferences {
    suspend fun changeTheme(isDark: Boolean)
    val themeFlow: Flow<Boolean>
    suspend fun setLanguage(language: String)
    val languageFlow: Flow<String>
}

class AppPreferencesImpl(private val dataStore: DataStore<Preferences>) : AppPreferences {

    private object Keys {
        val THEME = stringPreferencesKey("theme")
        val LANGUAGE = stringPreferencesKey("language")
    }

    override suspend fun changeTheme(isDark: Boolean) {
        dataStore.edit { preferences ->
            preferences[Keys.THEME] = isDark.toString()
        }
    }

    override val themeFlow: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[Keys.THEME]?.toBoolean() ?: false
        }

    override suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[Keys.LANGUAGE] = language
        }
    }

    override val languageFlow: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[Keys.LANGUAGE] ?: "en"
        }
}
