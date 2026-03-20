package com.bearminds.scaffold.root.vm

import com.bearminds.architecture.BaseViewModel
import com.bearminds.architecture.StyledSnackbarData

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
            is Event.OnGreetClicked -> {
                setState { copy(greeting = "Hello from MVI!") }
                setEffect {
                    SnackbarEffect(StyledSnackbarData(message = "Greeting updated"))
                }
            }
        }
    }

    sealed interface Event : ViewEvent {
        data object OnSettingsClicked : Event
        data object OnGreetClicked : Event
    }

    data class State(
        val title: String = "KMP Scaffold",
        val greeting: String = "Tap the button below",
    ) : ViewState

    sealed interface Effect : ViewEffect {
        data object NavigateToSettings : Effect
    }
}
