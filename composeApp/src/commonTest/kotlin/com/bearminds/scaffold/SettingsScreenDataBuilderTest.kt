package com.bearminds.scaffold

import com.bearminds.scaffold.settings.view.SettingsScreenDataBuilder
import com.bearminds.scaffold.settings.vm.SettingsContract
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class SettingsScreenDataBuilderTest {

    private val builder = SettingsScreenDataBuilder()

    @Test
    fun buildEmptyScreenReturnsDefaults() {
        val data = builder.buildEmptyScreen()
        assertFalse(data.isDarkTheme)
        assertEquals("en", data.language)
    }

    @Test
    fun buildScreenMapsStateToData() {
        val state = SettingsContract.State(
            isDarkTheme = true,
            language = "de",
        )
        val data = builder.buildScreen(state = state, onEvent = {})

        assertTrue(data.isDarkTheme)
        assertEquals("de", data.language)
    }

    @Test
    fun buildScreenWiresThemeToggleEvent() {
        val events = mutableListOf<SettingsContract.Event>()
        val state = SettingsContract.State()
        val data = builder.buildScreen(state = state, onEvent = { events.add(it) })

        data.onThemeToggled(true)

        assertEquals(1, events.size)
        val event = events[0] as SettingsContract.Event.OnThemeToggled
        assertTrue(event.isDark)
    }

    @Test
    fun buildScreenWiresLanguageChangeEvent() {
        val events = mutableListOf<SettingsContract.Event>()
        val state = SettingsContract.State()
        val data = builder.buildScreen(state = state, onEvent = { events.add(it) })

        data.onLanguageChanged("fr")

        assertEquals(1, events.size)
        val event = events[0] as SettingsContract.Event.OnLanguageChanged
        assertEquals("fr", event.language)
    }
}
