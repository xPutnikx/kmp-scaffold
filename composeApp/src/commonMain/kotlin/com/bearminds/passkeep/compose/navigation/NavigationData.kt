package com.bearminds.passkeep.compose.navigation

data class NavigationData(
    val route: NavigationRoute,
    val params: Map<String, Any> = emptyMap(),
)

interface NavigationRoute {
    val route: String
}

enum class AppNavigationRoute(override val route: String) : NavigationRoute {
    ROOT("/"),
    PASSWORDS("/passwords"),
    GENERATOR("/generator"),
    ANALYZER("/analyzer"),
    SETTINGS("/settings"),
}
