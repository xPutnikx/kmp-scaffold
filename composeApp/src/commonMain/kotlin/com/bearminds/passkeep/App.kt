package com.bearminds.passkeep

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.bearminds.passkeep.compose.navigation.AppNavigationRoute
import com.bearminds.passkeep.root.RootScreenDestination
import com.bearminds.passkeep.storage.AppPreferences
import com.bearminds.passkeep.theme.AppTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator

@Composable
fun App(
    modifier: Modifier = Modifier,
    systemStyle: Boolean,
    viewModel: AppViewModel
) {
    val darkTheme by viewModel.isDarkTheme().collectAsState(initial = systemStyle)

    PreComposeApp {
        AppTheme(darkTheme) {
            val navigator = rememberNavigator()

            NavHost(
                modifier = modifier,
                navigator = navigator,
                initialRoute = AppNavigationRoute.ROOT.route
            ) {
                scene(AppNavigationRoute.ROOT.route) {
                    RootScreenDestination(navigator)
                }

                scene(AppNavigationRoute.PASSWORDS.route) {
                    // TODO: Implement passwords screen
                    Text("Passwords Screen")
                }

                scene(AppNavigationRoute.GENERATOR.route) {
                    // TODO: Implement generator screen
                    Text("Generator Screen")
                }

                scene(AppNavigationRoute.ANALYZER.route) {
                    // TODO: Implement analyzer screen
                    Text("Analyzer Screen")
                }

                scene(AppNavigationRoute.SETTINGS.route) {
                    // TODO: Implement settings screen
                    Text("Settings Screen")
                }
            }
        }
    }
}

class AppViewModel(
    private val applicationScope: CoroutineScope,
    private val appPreferences: AppPreferences,
) : ViewModel() {

    fun isDarkTheme(): Flow<Boolean> = appPreferences.themeFlow

    fun languageCode(): Flow<String> = appPreferences.languageFlow
}
