package com.zaid.zavi.core.navigation

sealed class Screen(val route: String) {

    object LoginScreen : Screen("login_screen")
    object RegisterScreen : Screen("register_screen")

    object HomeScreen : Screen("home_screen")
    object SearchScreen : Screen("search_screen")

    object MyCartScreen : Screen("my_cart_screen")
    object ProfileScreen : Screen("profile_screen")
}