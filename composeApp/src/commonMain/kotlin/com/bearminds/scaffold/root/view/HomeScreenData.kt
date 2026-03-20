package com.bearminds.scaffold.root.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bearminds.architecture.ComposableData

class HomeScreenData(
    val title: String = "",
    val greeting: String = "",
    val onGreetClicked: () -> Unit = {},
    val onSettingsClicked: () -> Unit = {},
) : ComposableData {

    @Composable
    override fun Composable(modifier: Modifier) {
        Column(
            modifier = modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
            )

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
