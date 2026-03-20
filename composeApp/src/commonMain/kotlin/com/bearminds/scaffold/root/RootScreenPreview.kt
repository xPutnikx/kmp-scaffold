package com.bearminds.scaffold.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bearminds.architecture.RootScreen
import com.bearminds.scaffold.root.view.HomeScreenDataBuilder
import com.bearminds.scaffold.root.vm.RootScreenContract
import com.bearminds.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun HomeScreenPreviewLight() {
    AppTheme(isDarkTheme = false) {
        val data = HomeScreenDataBuilder().buildScreen(
            state = RootScreenContract.State(),
            onEvent = {},
        )
        RootScreen(
            showToolbar = true,
            title = { Text("KMP Scaffold") },
        ) {
            data.Composable(modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreviewDark() {
    AppTheme(isDarkTheme = true) {
        val data = HomeScreenDataBuilder().buildScreen(
            state = RootScreenContract.State(greeting = "Hello from MVI!"),
            onEvent = {},
        )
        RootScreen(
            showToolbar = true,
            title = { Text("KMP Scaffold") },
        ) {
            data.Composable(modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreviewEmpty() {
    AppTheme(isDarkTheme = false) {
        val data = HomeScreenDataBuilder().buildEmptyScreen()
        RootScreen(
            showToolbar = true,
            title = { Text("KMP Scaffold") },
        ) {
            data.Composable(modifier = Modifier.fillMaxSize())
        }
    }
}
