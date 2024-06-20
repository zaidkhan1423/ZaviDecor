package com.zaid.zavi.core.navigation

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.zaid.zavi.core.navigation.nav_graphs.authGraph
import com.zaid.zavi.core.navigation.nav_graphs.topLevelGraph
import com.zaid.zavi.feature_auth.presentation.splash_screen.SplashScreen
import kotlinx.coroutines.delay

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    isLoggedIn: Boolean,
    navHostController: NavHostController,
    onShowSnackBar: suspend (message: String, actionLabel: String?, duration: SnackbarDuration) -> Boolean,
) {

    var showSplashScreen by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(2000L)
        showSplashScreen = false
    }

    if (showSplashScreen) {
        SplashScreen()
    } else {
        NavHost(
            navController = navHostController,
            startDestination = if (isLoggedIn) NavGraphRoutes.TOP_LEVEL else NavGraphRoutes.AUTH
        ) {
            authGraph(navController = navHostController, onShowSnackBar = onShowSnackBar)
            topLevelGraph(navController = navHostController)
        }
    }

}

object NavGraphRoutes {
    const val AUTH = "auth_route"
    const val TOP_LEVEL = "top_level_route"
}
