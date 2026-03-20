package com.bearminds.scaffold.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bearminds.scaffold.root.HomeScreen
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainNavGraph(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Home,
        modifier = modifier,
    ) {
        composable<NavigationRoute.Home>(
            enterTransition = { subtleLeftEnter() },
            exitTransition = { subtleLeftExit() },
            popEnterTransition = { subtleLeftEnter() },
            popExitTransition = { leftToRightExit() },
        ) {
            HomeScreen(
                viewModel = koinViewModel(),
                onNavigateToSettings = {
                    navController.navigate(NavigationRoute.Settings)
                },
            )
        }

        composable<NavigationRoute.Settings>(
            enterTransition = { rightToLeftEnter() },
            exitTransition = { leftToRightExit() },
            popEnterTransition = { subtleLeftEnter() },
            popExitTransition = { leftToRightExit() },
        ) {
            // TODO: Settings screen
        }
    }
}
