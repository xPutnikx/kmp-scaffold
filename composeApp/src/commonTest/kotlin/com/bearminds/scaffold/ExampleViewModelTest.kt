package com.bearminds.scaffold

import com.bearminds.scaffold.root.vm.RootScreenViewModel
import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleViewModelTest {

    @Test
    fun initialStateHasExpectedTitle() {
        val viewModel = RootScreenViewModel()
        assertEquals("KMP Scaffold", viewModel.viewState.value.title)
    }

    @Test
    fun initialStateHasDefaultGreeting() {
        val viewModel = RootScreenViewModel()
        assertEquals("Tap the button below", viewModel.viewState.value.greeting)
    }
}
