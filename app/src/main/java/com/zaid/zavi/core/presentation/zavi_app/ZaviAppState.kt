package com.zaid.zavi.core.presentation.zavi_app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.zaid.zavi.core.navigation.Screen
import com.zaid.zavi.core.navigation.TopLevelDestination
import com.zaid.zavi.core.utils.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberZaviAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): ZaviAppState {
    return remember(
        networkMonitor,
        coroutineScope,
        navController
    ) {
        ZaviAppState(
            networkMonitor,
            coroutineScope,
            navController
        )
    }
}

class ZaviAppState(
    networkMonitor: NetworkMonitor,
    coroutineScope: CoroutineScope,
    val navController: NavHostController,
    ) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.values().asList()

    val shouldShowBottomBar: Boolean
        @Composable get() = when(currentDestination?.route) {
            Screen.HomeScreen.route -> true
            Screen.SearchScreen.route -> true
            Screen.MyCartScreen.route -> true
            Screen.ProfileScreen.route -> true
            else -> false
        }

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false,
        )

}