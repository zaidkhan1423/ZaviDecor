package com.zaid.zavi.core.presentation.zavi_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import com.google.firebase.auth.FirebaseAuth
import com.zaid.zavi.R
import com.zaid.zavi.core.navigation.AppNavHost
import com.zaid.zavi.core.navigation.TopLevelDestination
import com.zaid.zavi.core.presentation.navigation_bar.ZaviNavigationBar
import com.zaid.zavi.core.presentation.navigation_bar.ZaviNavigationItem
import com.zaid.zavi.core.utils.Icon
import com.zaid.zavi.core.utils.NetworkMonitor

@Composable
fun ZaviApp(
    networkMonitor: NetworkMonitor,
    firebaseAuth: FirebaseAuth,
    appState: ZaviAppState = rememberZaviAppState(
        networkMonitor = networkMonitor
    )
) {

    val snackBarHostState = remember { SnackbarHostState() }
    val isLoggedIn = firebaseAuth.currentUser != null
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    // If user is not connected to the internet show a snack bar to inform them.
    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackBarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        bottomBar = {
            if (appState.shouldShowBottomBar) {
                ZaviBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = { topLevelDestination ->
                        appState.navController.navigate(topLevelDestination.route) {
                            popUpTo(0)
                        }
                    },
                    currentDestination = appState.currentDestination
                )
            }
        }
    ) { paddingValues ->
        Box(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AppNavHost(
                isLoggedIn = isLoggedIn,
                navHostController = appState.navController,
                onShowSnackBar = { message, action, duration ->
                    snackBarHostState.showSnackbar(
                        message = message,
                        actionLabel = action,
                        duration = duration,
                        withDismissAction = duration == SnackbarDuration.Indefinite
                    ) == SnackbarResult.ActionPerformed
                })
        }
    }
}

@Composable
private fun ZaviBottomBar(
    destinations: List<TopLevelDestination>,
    onNavigateToDestination: (TopLevelDestination) -> Unit,
    currentDestination: NavDestination?,
    modifier: Modifier = Modifier
) {

    ZaviNavigationBar(
        modifier = modifier.height(55.dp)
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination?.route == destination.route

            ZaviNavigationItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    when (icon) {
                        is Icon.ImageVectorIcon -> Image(
                            modifier = Modifier.size(30.dp),
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )

                        is Icon.DrawableResourceIcon -> Image(
                            modifier = Modifier.size(30.dp),
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    }
}