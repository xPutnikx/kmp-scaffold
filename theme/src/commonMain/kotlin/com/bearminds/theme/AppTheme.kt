package com.bearminds.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

val LocalAppColors = staticCompositionLocalOf { LightAppColors }
val LocalIsDarkTheme = staticCompositionLocalOf { false }

val MaterialTheme.appColors: AppColors
    @Composable
    @ReadOnlyComposable
    get() = LocalAppColors.current

private val LightColorScheme = lightColorScheme(
    primary = LightAppColors.primary,
    onPrimary = LightAppColors.onPrimary,
    primaryContainer = LightAppColors.primaryContainer,
    onPrimaryContainer = LightAppColors.onPrimaryContainer,
    secondary = LightAppColors.secondary,
    onSecondary = LightAppColors.onSecondary,
    background = LightAppColors.background,
    onBackground = LightAppColors.onBackground,
    surface = LightAppColors.surface,
    onSurface = LightAppColors.onSurface,
    error = LightAppColors.error,
    onError = LightAppColors.onError,
)

private val DarkColorScheme = darkColorScheme(
    primary = DarkAppColors.primary,
    onPrimary = DarkAppColors.onPrimary,
    primaryContainer = DarkAppColors.primaryContainer,
    onPrimaryContainer = DarkAppColors.onPrimaryContainer,
    secondary = DarkAppColors.secondary,
    onSecondary = DarkAppColors.onSecondary,
    background = DarkAppColors.background,
    onBackground = DarkAppColors.onBackground,
    surface = DarkAppColors.surface,
    onSurface = DarkAppColors.onSurface,
    error = DarkAppColors.error,
    onError = DarkAppColors.onError,
)

private val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp),
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(24.dp),
)

@Composable
fun AppTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isDarkTheme) DarkColorScheme else LightColorScheme
    val appColors = if (isDarkTheme) DarkAppColors else LightAppColors

    CompositionLocalProvider(
        LocalAppColors provides appColors,
        LocalIsDarkTheme provides isDarkTheme,
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content,
        )
    }
}
