package com.bearminds.scaffold.settings.vm

import com.bearminds.architecture.BaseViewModel
import com.bearminds.scaffold.settings.view.SettingsScreenData

class SettingsContract {

    data class State(
        val isDarkTheme: Boolean = false,
        val language: String = "en",
        val data: SettingsScreenData = SettingsScreenData(),
    ) : BaseViewModel.ViewState

    sealed interface Event : BaseViewModel.ViewEvent {
        data object OnInitialize : Event
        data object OnBack : Event
        data class OnThemeToggled(val isDark: Boolean) : Event
        data class OnLanguageChanged(val language: String) : Event
    }

    sealed interface Effect : BaseViewModel.ViewEffect {
        data object NavigateBack : Effect
    }
}
