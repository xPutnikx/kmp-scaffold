package com.bearminds.passkeep.theme.switcher.vm

import com.bearminds.passkeep.views.BaseViewModel

class ThemeSwitcherContract {
    sealed interface Event : BaseViewModel.ViewEvent {
        data object OnLoadThemePreference : Event
        data class OnToggleThemePreference(val isDarkTheme: Boolean) : Event
    }

    data class State(
        val isDarkTheme: Boolean = false
    ) : BaseViewModel.ViewState
}
