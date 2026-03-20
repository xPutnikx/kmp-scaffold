package com.bearminds.scaffold.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

    HomeScreenContent(
        title = state.title,
        greeting = state.greeting,
        onGreetClicked = { viewModel.onEvent(RootScreenViewModel.Event.OnGreetClicked) },
        onSettingsClicked = { viewModel.onEvent(RootScreenViewModel.Event.OnSettingsClicked) },
    )
}
