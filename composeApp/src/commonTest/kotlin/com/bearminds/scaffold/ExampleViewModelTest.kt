package com.bearminds.scaffold

import com.bearminds.scaffold.root.vm.RootScreenViewModel
import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleViewModelTest {

    @Test
    fun initialStateHasExpectedTitle() {
        val viewModel = RootScreenViewModel()
        assertEquals("Welcome to KMP Scaffold", viewModel.viewState.value.title)
    }

    @Test
    fun stateIsNonNullableFromStart() {
        val viewModel = RootScreenViewModel()
        // BaseViewModel uses non-nullable StateFlow — no null check needed
        val state: RootScreenViewModel.State = viewModel.viewState.value
        assertEquals(false, state.title.isEmpty())
    }
}
