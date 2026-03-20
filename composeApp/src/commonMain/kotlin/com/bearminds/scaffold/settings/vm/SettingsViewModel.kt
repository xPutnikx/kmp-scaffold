package com.bearminds.scaffold.settings.vm

import androidx.lifecycle.viewModelScope
import com.bearminds.architecture.BaseViewModel
import com.bearminds.scaffold.settings.view.SettingsScreenDataBuilder
import com.bearminds.scaffold.storage.AppPreferences
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val dataBuilder: SettingsScreenDataBuilder,
    private val appPreferences: AppPreferences,
) : BaseViewModel<SettingsContract.Event, SettingsContract.State>() {

    override val initialState: SettingsContract.State = SettingsContract.State(
        data = dataBuilder.buildEmptyScreen()
    )

    override fun handleEvent(event: SettingsContract.Event) {
        when (event) {
            is SettingsContract.Event.OnInitialize -> subscribeToPreferences()
            is SettingsContract.Event.OnBack -> setEffect { NavigateBackEffect }
            is SettingsContract.Event.OnThemeToggled -> {
                viewModelScope.launch {
                    appPreferences.setTheme(event.isDark)
                }
            }
            is SettingsContract.Event.OnLanguageChanged -> {
                viewModelScope.launch {
                    appPreferences.setLanguage(event.language)
                }
            }
        }
    }

    private fun subscribeToPreferences() {
        appPreferences.themeFlow
            .distinctUntilChanged()
            .onEach { isDark ->
                setState { copy(isDarkTheme = isDark) }
                rebuildScreen()
            }
            .launchIn(viewModelScope)

        appPreferences.languageFlow
            .distinctUntilChanged()
            .onEach { language ->
                setState { copy(language = language) }
                rebuildScreen()
            }
            .launchIn(viewModelScope)
    }

    private fun rebuildScreen() {
        val currentState = viewState.value
        val data = dataBuilder.buildScreen(
            state = currentState,
            onEvent = ::handleEvent,
        )
        setState { copy(data = data) }
    }
}
