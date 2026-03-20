package com.bearminds.scaffold.root.view

import com.bearminds.scaffold.root.vm.RootScreenContract

class HomeScreenDataBuilder {

    fun buildEmptyScreen() = HomeScreenData()

    fun buildScreen(
        state: RootScreenContract.State,
        onEvent: (RootScreenContract.Event) -> Unit,
    ): HomeScreenData {
        return HomeScreenData(
            title = state.title,
            greeting = state.greeting,
            onGreetClicked = { onEvent(RootScreenContract.Event.OnGreetClicked) },
            onSettingsClicked = { onEvent(RootScreenContract.Event.OnSettingsClicked) },
        )
    }
}
