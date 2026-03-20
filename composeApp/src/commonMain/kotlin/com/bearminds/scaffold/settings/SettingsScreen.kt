package com.bearminds.scaffold.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBackIos
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bearminds.architecture.RootScreen
import com.bearminds.scaffold.settings.vm.SettingsContract
import com.bearminds.scaffold.settings.vm.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateBack: () -> Unit,
) {
    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(SettingsContract.Event.OnInitialize)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is SettingsContract.Effect.NavigateBack -> onNavigateBack()
                else -> {}
            }
        }
    }

    RootScreen(
        showToolbar = true,
        title = { Text("Settings") },
        effects = viewModel.effect,
        backAction = {
            IconButton(onClick = { viewModel.onEvent(SettingsContract.Event.OnBack) }) {
                Icon(
                    Icons.AutoMirrored.Rounded.ArrowBackIos,
                    contentDescription = "Back",
                )
            }
        },
    ) {
        state.data.Composable(modifier = Modifier.fillMaxSize())
    }
}
