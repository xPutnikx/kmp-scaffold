package com.bearminds.scaffold.root.vm

import com.bearminds.architecture.BaseViewModel
import com.bearminds.architecture.StyledSnackbarData
import com.bearminds.scaffold.root.view.HomeScreenDataBuilder

class RootScreenViewModel(
    private val dataBuilder: HomeScreenDataBuilder,
) : BaseViewModel<RootScreenContract.Event, RootScreenContract.State>() {

    override val initialState: RootScreenContract.State = RootScreenContract.State(
        data = dataBuilder.buildEmptyScreen()
    )

    init {
        rebuildScreen()
    }

    override fun handleEvent(event: RootScreenContract.Event) {
        when (event) {
            is RootScreenContract.Event.OnSettingsClicked -> {
                setEffect { RootScreenContract.Effect.NavigateToSettings }
            }
            is RootScreenContract.Event.OnGreetClicked -> {
                setState { copy(greeting = "Hello from MVI!") }
                rebuildScreen()
                setEffect {
                    SnackbarEffect(StyledSnackbarData(message = "Greeting updated"))
                }
            }
        }
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
