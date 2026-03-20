package com.bearminds.architecture

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

interface ComposableData {

    @Composable
    fun Composable(modifier: Modifier)

    @Composable
    fun Composable() = Composable(Modifier)
}
