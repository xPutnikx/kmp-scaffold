package com.bearminds.scaffold.settings.view

import com.bearminds.scaffold.settings.vm.SettingsContract

class SettingsScreenDataBuilder {

    fun buildEmptyScreen() = SettingsScreenData()

    fun buildScreen(
        state: SettingsContract.State,
        onEvent: (SettingsContract.Event) -> Unit,
    ): SettingsScreenData {
        return SettingsScreenData(
            isDarkTheme = state.isDarkTheme,
            language = state.language,
            onThemeToggled = { isDark -> onEvent(SettingsContract.Event.OnThemeToggled(isDark)) },
            onLanguageChanged = { lang -> onEvent(SettingsContract.Event.OnLanguageChanged(lang)) },
        )
    }
}
