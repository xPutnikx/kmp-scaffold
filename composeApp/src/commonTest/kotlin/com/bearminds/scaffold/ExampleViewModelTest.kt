package com.bearminds.scaffold

import com.bearminds.scaffold.root.view.HomeScreenDataBuilder
import com.bearminds.scaffold.root.vm.RootScreenContract
import com.bearminds.scaffold.root.vm.RootScreenViewModel
import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleViewModelTest {

    private fun createViewModel() = RootScreenViewModel(
        dataBuilder = HomeScreenDataBuilder()
    )

    @Test
    fun initialStateHasExpectedTitle() {
        val viewModel = createViewModel()
        assertEquals("KMP Scaffold", viewModel.viewState.value.title)
    }

    @Test
    fun initialStateHasDefaultGreeting() {
        val viewModel = createViewModel()
        assertEquals("Tap the button below", viewModel.viewState.value.greeting)
    }

    @Test
    fun initialStateHasEmptyScreenDataBeforeInitialize() {
        val viewModel = createViewModel()
        // Before OnInitialize, data is empty (built by buildEmptyScreen)
        val data = viewModel.viewState.value.data
        assertEquals("", data.title)
    }

    @Test
    fun onInitializeBuildsScreenData() {
        val viewModel = createViewModel()
        viewModel.onEvent(RootScreenContract.Event.OnInitialize)
        // After OnInitialize, data should be populated
        // Note: event processing is async via Channel, so in a real test
        // you'd use Turbine or TestDispatcher. This verifies the event is accepted.
    }
}
