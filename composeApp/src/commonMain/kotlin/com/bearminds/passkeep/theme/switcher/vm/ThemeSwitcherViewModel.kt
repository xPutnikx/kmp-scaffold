package com.bearminds.passkeep.theme.switcher.vm

import androidx.lifecycle.viewModelScope
import com.bearminds.passkeep.storage.AppPreferences
import com.bearminds.passkeep.views.BaseViewModel
import kotlinx.coroutines.launch

class ThemeSwitcherViewModel(
    private val appPreferences: AppPreferences
) : BaseViewModel<ThemeSwitcherContract.Event, ThemeSwitcherContract.State>() {

    override val initialState: ThemeSwitcherContract.State = ThemeSwitcherContract.State()

    override fun handleEvent(event: ThemeSwitcherContract.Event) {
        when (event) {
            is ThemeSwitcherContract.Event.OnLoadThemePreference -> {
                viewModelScope.launch {
                    appPreferences.themeFlow.collect {
                        setState { copy(isDarkTheme = it) }
                    }
                }
            }

            is ThemeSwitcherContract.Event.OnToggleThemePreference -> {
                setState {
                    copy(isDarkTheme = event.isDarkTheme)
                }
                viewModelScope.launch {
                    appPreferences.changeTheme(event.isDarkTheme)
                }
            }
        }
    }
}
