package com.zaid.zavi.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zaid.zavi.navigation.nav_graphs.authGraph
import com.zaid.zavi.navigation.nav_graphs.topLevelGraph

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navHostController: NavHostController
) {
    NavHost(navController = navHostController, startDestination = NavGraphRoutes.TOP_LEVEL) {
        authGraph(navController = navHostController)
        topLevelGraph(navController = navHostController)
    }
}

object NavGraphRoutes {
    const val AUTH = "auth_route"
    const val TOP_LEVEL = "top_level_route"
}