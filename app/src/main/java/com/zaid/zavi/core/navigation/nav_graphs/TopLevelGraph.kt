package com.zaid.zavi.core.navigation.nav_graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zaid.zavi.core.navigation.NavGraphRoutes
import com.zaid.zavi.core.navigation.Screen
import com.zaid.zavi.ui.home_screen.HomeScreen
import com.zaid.zavi.ui.my_cart_screen.MyCartScreen
import com.zaid.zavi.ui.profile_screen.ProfileScreen
import com.zaid.zavi.ui.search_screen.SearchScreen

fun NavGraphBuilder.topLevelGraph(
    navController: NavController
) {
    navigation(startDestination = Screen.HomeScreen.route, route = NavGraphRoutes.TOP_LEVEL) {

        composable(route = Screen.HomeScreen.route) {
            HomeScreen()
        }

        composable(route = Screen.SearchScreen.route) {
            SearchScreen()
        }

        composable(route = Screen.MyCartScreen.route) {
            MyCartScreen()
        }

        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen()
        }
    }
}