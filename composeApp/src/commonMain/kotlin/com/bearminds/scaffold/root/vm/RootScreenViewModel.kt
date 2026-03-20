package com.bearminds.scaffold.root.vm

import com.bearminds.architecture.BaseViewModel

class RootScreenViewModel : BaseViewModel<
    RootScreenViewModel.Event,
    RootScreenViewModel.State
    >() {

    override val initialState: State = State()

    override fun handleEvent(event: Event) {
        when (event) {
            is Event.OnSettingsClicked -> {
                setEffect { Effect.NavigateToSettings }
            }
        }
    }

    sealed interface Event : ViewEvent {
        data object OnSettingsClicked : Event
    }

    data class State(
        val title: String = "Welcome to KMP Scaffold"
    ) : ViewState

    sealed interface Effect : ViewEffect {
        data object NavigateToSettings : Effect
    }
}
