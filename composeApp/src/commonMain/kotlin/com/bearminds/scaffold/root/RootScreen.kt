package com.bearminds.scaffold.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.bearminds.scaffold.root.vm.RootScreenViewModel

@Composable
fun RootScreen(
    viewModel: RootScreenViewModel,
    onNavigateToSettings: () -> Unit,
) {
    val state by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is RootScreenViewModel.Effect.NavigateToSettings -> onNavigateToSettings()
                else -> {}
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = state.title,
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
