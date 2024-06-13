package com.zaid.zavi.feature_auth.presentation.login_screen

data class LoginScreenUiState(
    val loginLoading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null,
    val resetPasswordLoading: Boolean = false,
    val shouldShowResetPasswordDialog: Boolean = false,
    val isEmailEmpty: Boolean = false,
    val isPasswordEmpty: Boolean = false,
    val email: String = "",
    val password: String = "",
    val emailForResetPassword: String = "",
    val isEmailForResetPasswordEmpty: Boolean = false
)
