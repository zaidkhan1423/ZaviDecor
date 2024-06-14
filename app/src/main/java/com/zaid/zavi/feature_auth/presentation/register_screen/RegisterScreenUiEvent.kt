package com.zaid.zavi.feature_auth.presentation.register_screen

sealed interface RegisterScreenUiEvent {

    data class OnNameChange(val name: String): RegisterScreenUiEvent
    data class OnEmailChange(val email: String): RegisterScreenUiEvent
    data class OnPasswordChange(val password: String): RegisterScreenUiEvent
    data class OnConfirmPasswordChange(val confirmPassword: String): RegisterScreenUiEvent
    data class EmailEmptyStateChanged(val isEmailEmpty: Boolean): RegisterScreenUiEvent
    data class NameEmptyStateChanged(val isNameEmpty: Boolean): RegisterScreenUiEvent
    data class PasswordEmptyStateChanged(val isPasswordEmpty: Boolean): RegisterScreenUiEvent
    data class PasswordMatchStateChanged(val isPasswordMatch: Boolean): RegisterScreenUiEvent
    object OnRegisterUserClick: RegisterScreenUiEvent
    object OnMessageDisplayed : RegisterScreenUiEvent

}