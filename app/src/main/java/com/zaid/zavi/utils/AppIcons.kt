package com.zaid.zavi.utils

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.zaid.zavi.R

object AppIcons {
    val HomeSelected = R.drawable.selected_home
    val HomeUnselected = R.drawable.unselected_home
    val ProfileSelected = R.drawable.selected_profile
    val ProfileUnselected = R.drawable.unselected_profile
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}