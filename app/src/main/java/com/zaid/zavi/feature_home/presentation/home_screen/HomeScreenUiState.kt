package com.zaid.zavi.feature_home.presentation.home_screen

import com.zaid.zavi.feature_home.data.model.response.Products

data class HomeScreenUiState(
    val productsLoading: Boolean = false,
    val popularProductsLoading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null,
    val products: List<Products> = emptyList(),
    val popularProducts: List<Products> = emptyList(),
    val selectedCategory: String = ""
)
