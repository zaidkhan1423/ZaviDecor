package com.zaid.zavi.core.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.zaid.zavi.R

object AppIcons {
    val HomeSelected = R.drawable.selected_home
    val HomeUnselected = R.drawable.unselected_home
    val ProfileSelected = R.drawable.selected_profile
    val ProfileUnselected = R.drawable.unselected_profile
    val SearchSelected = R.drawable.selected_search
    val SearchUnselected = R.drawable.unselected_search
    val CartSelected = R.drawable.selected_cart
    val CartUnselected = R.drawable.unselected_cart
    val NavDrawerIcon = R.drawable.menu
    val OrderIcon = R.drawable.ic_order
    val WishlistIcon = R.drawable.ic_wishlist
    val SelectedLikeIcon = R.drawable.selected_like
    val UnselectedLikeIcon = R.drawable.unselected_like
    val VisibilityOn = R.drawable.ic_visibility_on
    val VisibilityOff = R.drawable.ic_visibility_off
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}