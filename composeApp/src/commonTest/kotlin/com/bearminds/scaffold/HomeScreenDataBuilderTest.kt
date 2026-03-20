package com.bearminds.scaffold

import com.bearminds.scaffold.root.view.HomeScreenDataBuilder
import com.bearminds.scaffold.root.vm.RootScreenContract
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HomeScreenDataBuilderTest {

    private val builder = HomeScreenDataBuilder()

    @Test
    fun buildEmptyScreenReturnsEmptyData() {
        val data = builder.buildEmptyScreen()
        assertEquals("", data.title)
        assertEquals("", data.greeting)
    }

    @Test
    fun buildScreenMapsStateToData() {
        val state = RootScreenContract.State(
            title = "Test Title",
            greeting = "Test Greeting",
        )
        val data = builder.buildScreen(state = state, onEvent = {})

        assertEquals("Test Title", data.title)
        assertEquals("Test Greeting", data.greeting)
    }

    @Test
    fun buildScreenWiresEventCallbacks() {
        val events = mutableListOf<RootScreenContract.Event>()
        val state = RootScreenContract.State()
        val data = builder.buildScreen(state = state, onEvent = { events.add(it) })

        data.onGreetClicked()
        data.onSettingsClicked()

        assertEquals(2, events.size)
        assertTrue(events[0] is RootScreenContract.Event.OnGreetClicked)
        assertTrue(events[1] is RootScreenContract.Event.OnSettingsClicked)
    }
}
