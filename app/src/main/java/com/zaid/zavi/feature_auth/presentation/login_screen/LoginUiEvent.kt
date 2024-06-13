package com.zaid.zavi.feature_auth.presentation.login_screen

sealed interface LoginUiEvent {

    data class OnEmailChange(val email: String) : LoginUiEvent
    data class OnPasswordChange(val password: String) : LoginUiEvent
    data class OnEmailForResetPasswordChange(val emailForResetPassword: String): LoginUiEvent
    data class EmailForResetPasswordStateChange(val isEmailForResetPasswordEmpty: Boolean): LoginUiEvent
    data class EmailEmptyStateChanged(val isEmailEmpty: Boolean): LoginUiEvent
    data class PasswordEmptyStateChanged(val isPasswordEmpty: Boolean): LoginUiEvent
    data class OnResetPasswordDialogStateChanged(val shouldShowResetPasswordDialog: Boolean): LoginUiEvent
    object OnLoginClick : LoginUiEvent
    object OnResetPasswordClick: LoginUiEvent
    object OnMessageDisplayed : LoginUiEvent

}