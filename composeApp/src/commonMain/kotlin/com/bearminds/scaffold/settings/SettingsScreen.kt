package com.bearminds.scaffold.settings

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.bearminds.architecture.RootScreen
import com.bearminds.scaffold.settings.vm.SettingsContract
import com.bearminds.scaffold.settings.vm.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateBack: () -> Unit,
    backIcon: ImageVector,
) {
    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.onEvent(SettingsContract.Event.OnInitialize)
    }

    RootScreen(
        showToolbar = true,
        title = { Text("Settings") },
        effects = viewModel.effect,
        onNavigateBack = onNavigateBack,
        backAction = {
            IconButton(onClick = { viewModel.onEvent(SettingsContract.Event.OnBack) }) {
                Icon(backIcon, contentDescription = "Back")
            }
        },
    ) {
        state.data.Composable(modifier = Modifier.fillMaxSize())
    }
}
