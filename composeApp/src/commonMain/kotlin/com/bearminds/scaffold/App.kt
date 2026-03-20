package com.bearminds.scaffold

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.bearminds.scaffold.navigation.MainNavGraph
import com.bearminds.scaffold.storage.AppPreferences
import com.bearminds.theme.AppTheme
import org.koin.compose.koinInject

@Composable
fun App(
    modifier: Modifier = Modifier,
    systemIsDark: Boolean = false,
) {
    val appPreferences = koinInject<AppPreferences>()
    val isDarkTheme by appPreferences.themeFlow.collectAsState(initial = systemIsDark)

    AppTheme(isDarkTheme = isDarkTheme) {
        MainNavGraph(modifier = modifier)
    }
}
