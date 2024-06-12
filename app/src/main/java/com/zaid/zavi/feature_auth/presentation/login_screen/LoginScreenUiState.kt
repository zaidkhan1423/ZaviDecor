package com.zaid.zavi.feature_auth.presentation.login_screen

data class LoginScreenUiState(
    val loginLoading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null,
    val resetPasswordLoading: Boolean = false,
    val shouldHideResetPasswordDialog: Boolean = false
)
