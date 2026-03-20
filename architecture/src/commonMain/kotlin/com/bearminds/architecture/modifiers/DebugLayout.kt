package com.bearminds.architecture.modifiers

import androidx.compose.foundation.border
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Modifier.debugLayout(): Modifier =
    this.border(width = 1.dp, color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f))
