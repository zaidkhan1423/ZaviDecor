package com.zaid.zavi.feature_auth.presentation.register_screen

data class RegisterScreenUiState(
    val loading: Boolean = false,
    val shouldNavigate: Boolean = false,
    val snackBarMessage: String? = null,
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isPasswordBlank: Boolean = false,
    val isNameBlank: Boolean = false,
    val isEmailBlank: Boolean = false,
    val isPasswordMatch: Boolean = true
)
