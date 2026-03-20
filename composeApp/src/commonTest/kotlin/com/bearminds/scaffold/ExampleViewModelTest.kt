package com.bearminds.scaffold

import com.bearminds.scaffold.root.view.HomeScreenDataBuilder
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
    fun initialStateHasScreenData() {
        val viewModel = createViewModel()
        // DataBuilder builds screen data on init — data should have title and greeting
        val data = viewModel.viewState.value.data
        assertEquals("KMP Scaffold", data.title)
        assertEquals("Tap the button below", data.greeting)
    }
}
