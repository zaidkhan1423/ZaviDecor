package com.zaid.zavi.navigation

import com.zaid.zavi.utils.AppIcons
import com.zaid.zavi.utils.Icon

enum class TopLevelDestination(
    val selectedIcon: Icon,
    val unselectedIcon: Icon,
    val route: String
) {

    HOME(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeSelected),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeUnselected),
        route = Screen.HomeScreen.route
    ),
    SEARCH(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeSelected),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeUnselected),
        route = Screen.SearchScreen.route
    ),
    MY_CART(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeSelected),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.HomeUnselected),
        route = Screen.MyCartScreen.route
    ),
    PROFILE(
        selectedIcon = Icon.DrawableResourceIcon(AppIcons.ProfileSelected),
        unselectedIcon = Icon.DrawableResourceIcon(AppIcons.ProfileUnselected),
        route = Screen.ProfileScreen.route
    )

}