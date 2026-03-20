package com.bearminds.scaffold.storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.bearminds.core.analytics.ConsentProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface AppPreferences : ConsentProvider {
    val themeFlow: Flow<Boolean>
    val languageFlow: Flow<String>
    val isOnboardingCompletedFlow: Flow<Boolean>
    override val isAnalyticsEnabled: Flow<Boolean>

    suspend fun setTheme(isDark: Boolean)
    suspend fun setLanguage(language: String)
    suspend fun setOnboardingCompleted(completed: Boolean)
    suspend fun setAnalyticsEnabled(enabled: Boolean)
}

class AppPreferencesImpl(
    private val dataStore: DataStore<Preferences>
) : AppPreferences {

    override val themeFlow: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[Keys.THEME] ?: false
    }

    override val languageFlow: Flow<String> = dataStore.data.map { prefs ->
        prefs[Keys.LANGUAGE] ?: "en"
    }

    override val isOnboardingCompletedFlow: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[Keys.ONBOARDING_COMPLETED] ?: false
    }

    override val isAnalyticsEnabled: Flow<Boolean> = dataStore.data.map { prefs ->
        prefs[Keys.ANALYTICS_ENABLED] ?: true
    }

    override suspend fun setTheme(isDark: Boolean) {
        dataStore.edit { it[Keys.THEME] = isDark }
    }

    override suspend fun setLanguage(language: String) {
        dataStore.edit { it[Keys.LANGUAGE] = language }
    }

    override suspend fun setOnboardingCompleted(completed: Boolean) {
        dataStore.edit { it[Keys.ONBOARDING_COMPLETED] = completed }
    }

    override suspend fun setAnalyticsEnabled(enabled: Boolean) {
        dataStore.edit { it[Keys.ANALYTICS_ENABLED] = enabled }
    }

    private object Keys {
        val THEME = booleanPreferencesKey("theme")
        val LANGUAGE = stringPreferencesKey("language")
        val ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
        val ANALYTICS_ENABLED = booleanPreferencesKey("analytics_enabled")
    }
}
