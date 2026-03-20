package com.bearminds.scaffold.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.bearminds.architecture.RootScreen
import com.bearminds.scaffold.settings.view.SettingsScreenDataBuilder
import com.bearminds.scaffold.settings.vm.SettingsContract
import com.bearminds.theme.AppTheme
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
private fun SettingsScreenPreviewLight() {
    AppTheme(isDarkTheme = false) {
        val data = SettingsScreenDataBuilder().buildScreen(
            state = SettingsContract.State(isDarkTheme = false, language = "en"),
            onEvent = {},
        )
        RootScreen(
            showToolbar = true,
            title = { Text("Settings") },
            backAction = {
                IconButton(onClick = {}) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBackIos, contentDescription = "Back")
                }
            },
        ) {
            data.Composable(modifier = Modifier.fillMaxSize())
        }
    }
}

@Preview
@Composable
private fun SettingsScreenPreviewDark() {
    AppTheme(isDarkTheme = true) {
        val data = SettingsScreenDataBuilder().buildScreen(
            state = SettingsContract.State(isDarkTheme = true, language = "en"),
            onEvent = {},
        )
        RootScreen(
            showToolbar = true,
            title = { Text("Settings") },
            backAction = {
                IconButton(onClick = {}) {
                    Icon(Icons.AutoMirrored.Rounded.ArrowBackIos, contentDescription = "Back")
                }
            },
        ) {
            data.Composable(modifier = Modifier.fillMaxSize())
        }
    }
}
