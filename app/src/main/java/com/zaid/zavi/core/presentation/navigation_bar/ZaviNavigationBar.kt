package com.zaid.zavi.core.presentation.navigation_bar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ZaviNavigationBar(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit
) {
    NavigationBar(
        modifier = modifier,
        contentColor = Color.Unspecified,
        containerColor = ZaviNavigationDefaults.navigationContainerColor(),
        tonalElevation = 0.dp,
        content = content
    )
}

@Composable
fun RowScope.ZaviNavigationItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
) {
    NavigationBarItem(
        selected = selected,
        onClick = onClick,
        icon = if (selected) selectedIcon else icon,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = ZaviNavigationDefaults.navigationSelectedColor(),
            indicatorColor = MaterialTheme.colorScheme.background
        ),
        modifier = modifier
    )
}

object ZaviNavigationDefaults {

    @Composable
    fun navigationContainerColor() = MaterialTheme.colorScheme.background
    @Composable
    fun navigationSelectedColor() = MaterialTheme.colorScheme.primary

}