package com.bearminds.scaffold.root.vm

import com.bearminds.architecture.BaseViewModel
import com.bearminds.scaffold.root.view.HomeScreenData

class RootScreenContract {

    data class State(
        val title: String = "KMP Scaffold",
        val greeting: String = "Tap the button below",
        val data: HomeScreenData = HomeScreenData(),
    ) : BaseViewModel.ViewState

    sealed interface Event : BaseViewModel.ViewEvent {
        data object OnInitialize : Event
        data object OnSettingsClicked : Event
        data object OnGreetClicked : Event
    }

    sealed interface Effect : BaseViewModel.ViewEffect {
        data object NavigateToSettings : Effect
    }
}
