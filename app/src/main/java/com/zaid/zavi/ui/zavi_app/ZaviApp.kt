package com.zaid.zavi.ui.zavi_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import com.zaid.zavi.R
import com.zaid.zavi.navigation.AppNavHost
import com.zaid.zavi.navigation.TopLevelDestination
import com.zaid.zavi.ui.navigation_bar.ZaviNavigationBar
import com.zaid.zavi.ui.navigation_bar.ZaviNavigationItem
import com.zaid.zavi.utils.Icon
import com.zaid.zavi.utils.NetworkMonitor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ZaviApp(
    networkMonitor: NetworkMonitor,
    appState: ZaviAppState = rememberZaviAppState(
        networkMonitor = networkMonitor
    )
) {

    val snackbarHostState = remember { SnackbarHostState() }

    val isOffline by appState.isOffline.collectAsStateWithLifecycle()

    // If user is not connected to the internet show a snack bar to inform them.
    val notConnectedMessage = stringResource(R.string.not_connected)
    LaunchedEffect(isOffline) {
        if (isOffline) {
            snackbarHostState.showSnackbar(
                message = notConnectedMessage,
                duration = SnackbarDuration.Indefinite,
            )
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
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
            AppNavHost(navHostController = appState.navController)
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
        modifier = modifier
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
                        is Icon.ImageVectorIcon -> Icon(
                            imageVector = icon.imageVector,
                            contentDescription = null,
                        )

                        is Icon.DrawableResourceIcon -> Image(
                            painter = painterResource(id = icon.id),
                            contentDescription = null,
                        )
                    }
                }
            )
        }
    }

}