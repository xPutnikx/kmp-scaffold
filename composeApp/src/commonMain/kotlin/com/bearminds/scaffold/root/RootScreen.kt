package com.bearminds.scaffold.root

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bearminds.architecture.RootScreen
import com.bearminds.scaffold.root.vm.RootScreenViewModel

@Composable
fun HomeScreen(
    viewModel: RootScreenViewModel,
    onNavigateToSettings: () -> Unit,
) {
    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is RootScreenViewModel.Effect.NavigateToSettings -> onNavigateToSettings()
                else -> {} // Snackbar/Haptic handled by RootScreen's EffectProcessor
            }
        }
    }

    RootScreen(
        showToolbar = true,
        title = { Text(state.title) },
        effects = viewModel.effect,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            Text(
                text = state.greeting,
                style = MaterialTheme.typography.bodyLarge,
            )

            Button(onClick = { viewModel.onEvent(RootScreenViewModel.Event.OnGreetClicked) }) {
                Text("Greet")
            }

            Button(onClick = { viewModel.onEvent(RootScreenViewModel.Event.OnSettingsClicked) }) {
                Text("Settings")
            }
        }
    }
}
