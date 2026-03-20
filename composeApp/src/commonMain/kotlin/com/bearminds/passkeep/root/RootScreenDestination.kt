package com.bearminds.passkeep.root

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import moe.tlaster.precompose.navigation.Navigator
import org.koin.compose.viewmodel.koinViewModel
import com.bearminds.passkeep.root.vm.RootScreenViewModel

@Composable
fun RootScreenDestination(navigator: Navigator) {
    val viewModel = koinViewModel<RootScreenViewModel>()

    RootScreen(navigator)
}

@Composable
private fun RootScreen(navigator: Navigator) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to PassKeep",
            style = MaterialTheme.typography.headlineLarge
        )
    }
}
