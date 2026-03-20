package com.bearminds.architecture

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import org.jetbrains.compose.resources.StringResource

data class StyledSnackbarData(
    override val message: String,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val withDismissAction: Boolean = false,
): SnackbarVisuals

data class StyledSnackbarResourceData(
    val messageRes: StringResource,
    val actionLabel: String? = null,
    val duration: SnackbarDuration = SnackbarDuration.Short,
    val withDismissAction: Boolean = false,
)
