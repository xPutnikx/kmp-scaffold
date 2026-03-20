package com.bearminds.scaffold.navigation

import kotlinx.serialization.Serializable

sealed interface NavigationRoute {
    @Serializable data object Home : NavigationRoute
    @Serializable data object Settings : NavigationRoute
}
