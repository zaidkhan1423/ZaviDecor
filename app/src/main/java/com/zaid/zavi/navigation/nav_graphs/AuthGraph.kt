package com.zaid.zavi.navigation.nav_graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zaid.zavi.navigation.NavGraphRoutes
import com.zaid.zavi.navigation.Screen
import com.zaid.zavi.ui.login_screen.LoginScreen
import com.zaid.zavi.ui.register_screen.RegisterScreen

fun NavGraphBuilder.authGraph() {
    navigation(startDestination = Screen.RegisterScreen.route, route = NavGraphRoutes.AUTH) {

        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen()
        }

        composable(route = Screen.LoginScreen.route) {
            LoginScreen()
        }

    }
}