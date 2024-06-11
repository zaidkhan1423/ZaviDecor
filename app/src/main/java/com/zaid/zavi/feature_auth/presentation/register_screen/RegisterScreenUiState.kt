package com.zaid.zavi.feature_auth.presentation.register_screen

data class RegisterScreenUiState(
    val loading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null
)
