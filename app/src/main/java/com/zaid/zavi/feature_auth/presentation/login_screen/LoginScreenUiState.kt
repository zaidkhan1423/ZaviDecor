package com.zaid.zavi.feature_auth.presentation.login_screen

data class LoginScreenUiState(
    val loading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null
)
