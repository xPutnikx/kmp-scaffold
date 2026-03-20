package com.bearminds.core.analytics

interface UserAnalyticsManager {
    fun updateThemePreference(isDarkMode: Boolean)
    fun updateLanguagePreference(languageCode: String)
    fun updateOnboardingCompleted(completed: Boolean)
}
