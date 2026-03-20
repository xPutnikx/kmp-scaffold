package com.bearminds.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class AppColors(
    // Background
    val background: Color,
    val onBackground: Color,
    val surface: Color,
    val onSurface: Color,

    // Primary
    val primary: Color,
    val onPrimary: Color,
    val primaryContainer: Color,
    val onPrimaryContainer: Color,

    // Secondary
    val secondary: Color,
    val onSecondary: Color,

    // Error / Status
    val error: Color,
    val onError: Color,
    val positive: Color,
    val warning: Color,

    // Text hierarchy
    val textPrimary: Color,
    val textSecondary: Color,
    val textDisabled: Color,

    // Divider
    val divider: Color,
)

val LightAppColors = AppColors(
    background = Color(0xFFFEFEFE),
    onBackground = Color(0xFF1C1B1F),
    surface = Color(0xFFF5F5F5),
    onSurface = Color(0xFF1C1B1F),
    primary = Color(0xFF6750A4),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFEADDFF),
    onPrimaryContainer = Color(0xFF21005D),
    secondary = Color(0xFF625B71),
    onSecondary = Color(0xFFFFFFFF),
    error = Color(0xFFB3261E),
    onError = Color(0xFFFFFFFF),
    positive = Color(0xFF19B596),
    warning = Color(0xFFE8A33D),
    textPrimary = Color(0xFF1C1B1F),
    textSecondary = Color(0xFF49454F),
    textDisabled = Color(0xFFA0A0A0),
    divider = Color(0xFFE0E0E0),
)

val DarkAppColors = AppColors(
    background = Color(0xFF1C1B1F),
    onBackground = Color(0xFFE6E1E5),
    surface = Color(0xFF2B2930),
    onSurface = Color(0xFFE6E1E5),
    primary = Color(0xFFD0BCFF),
    onPrimary = Color(0xFF381E72),
    primaryContainer = Color(0xFF4F378B),
    onPrimaryContainer = Color(0xFFEADDFF),
    secondary = Color(0xFFCCC2DC),
    onSecondary = Color(0xFF332D41),
    error = Color(0xFFF2B8B5),
    onError = Color(0xFF601410),
    positive = Color(0xFF4ADE80),
    warning = Color(0xFFFBBF24),
    textPrimary = Color(0xFFE6E1E5),
    textSecondary = Color(0xFFCAC4D0),
    textDisabled = Color(0xFF6B6B6B),
    divider = Color(0xFF3D3D3D),
)
