package com.zaid.zavi.core.navigation.nav_graphs

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.zaid.zavi.core.navigation.NavGraphRoutes
import com.zaid.zavi.core.navigation.Screen
import com.zaid.zavi.feature_auth.presentation.login_screen.LoginScreen
import com.zaid.zavi.feature_auth.presentation.login_screen.LoginScreenViewModel
import com.zaid.zavi.feature_auth.presentation.register_screen.RegisterScreen
import com.zaid.zavi.feature_auth.presentation.register_screen.RegisterScreenViewModel

fun NavGraphBuilder.authGraph(
    navController: NavHostController,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean
) {
    navigation(startDestination = Screen.LoginScreen.route, route = NavGraphRoutes.AUTH) {

        composable(route = Screen.RegisterScreen.route) {

            val registerScreenViewModel: RegisterScreenViewModel = hiltViewModel()
            val registerScreenUiState by registerScreenViewModel.registerScreenUiState.collectAsStateWithLifecycle()

            RegisterScreen(
                navController = navController,
                uiState = registerScreenUiState,
                registerUser = registerScreenViewModel::signupUser,
                onShowSnackBar = onShowSnackBar,
            )
        }

        composable(route = Screen.LoginScreen.route) {

            val loginViewModel: LoginScreenViewModel = hiltViewModel()
            val loginScreenUiState by loginViewModel.loginScreenUiState.collectAsStateWithLifecycle()

            LoginScreen(
                navController = navController,
                uiState = loginScreenUiState,
                onEvent = loginViewModel::onEvent,
                onShowSnackBar = onShowSnackBar
            )
        }

    }
}