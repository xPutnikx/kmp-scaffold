package com.bearminds.scaffold

import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import com.bearminds.scaffold.di.appModule
import org.koin.core.context.startKoin

fun main() = application {
    startKoin {
        modules(appModule)
    }

    val state = WindowState(
        size = DpSize(400.dp, 900.dp),
        position = WindowPosition(200.dp, 100.dp)
    )

    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP Scaffold",
        state = state
    ) {
        App(systemIsDark = false)
    }
}
