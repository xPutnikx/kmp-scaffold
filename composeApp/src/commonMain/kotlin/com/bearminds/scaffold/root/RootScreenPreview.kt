package com.bearminds.scaffold.root

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bearminds.architecture.RootScreen
import com.bearminds.theme.AppTheme

@Preview
@Composable
private fun HomeScreenPreviewLight() {
    AppTheme(isDarkTheme = false) {
        HomeScreenContent(
            title = "KMP Scaffold",
            greeting = "Tap the button below",
            onGreetClicked = {},
            onSettingsClicked = {},
        )
    }
}

@Preview
@Composable
private fun HomeScreenPreviewDark() {
    AppTheme(isDarkTheme = true) {
        HomeScreenContent(
            title = "KMP Scaffold",
            greeting = "Hello from MVI!",
            onGreetClicked = {},
            onSettingsClicked = {},
        )
    }
}

@Composable
internal fun HomeScreenContent(
    title: String,
    greeting: String,
    onGreetClicked: () -> Unit,
    onSettingsClicked: () -> Unit,
) {
    RootScreen(
        showToolbar = true,
        title = { Text(title) },
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            Text(
                text = greeting,
                style = MaterialTheme.typography.bodyLarge,
            )

            Button(onClick = onGreetClicked) {
                Text("Greet")
            }

            Button(onClick = onSettingsClicked) {
                Text("Settings")
            }
        }
    }
}
