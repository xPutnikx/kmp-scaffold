package com.bearminds.scaffold.root

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bearminds.architecture.RootScreen
import com.bearminds.scaffold.root.vm.RootScreenContract
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
                is RootScreenContract.Effect.NavigateToSettings -> onNavigateToSettings()
                else -> {}
            }
        }
    }

    RootScreen(
        showToolbar = true,
        title = { Text(state.title) },
        effects = viewModel.effect,
    ) {
        state.data.Composable(modifier = Modifier.fillMaxSize())
    }
}
