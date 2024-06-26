package com.zaid.zavi.feature_auth.presentation.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaid.zavi.core.utils.Resource
import com.zaid.zavi.feature_auth.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginScreenUiState = MutableStateFlow(LoginScreenUiState())
    val loginScreenUiState = _loginScreenUiState.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnEmailChange -> _loginScreenUiState.update {
                it.copy(
                    email = event.email
                )
            }

            is LoginUiEvent.OnPasswordChange -> _loginScreenUiState.update {
                it.copy(
                    password = event.password
                )
            }

            LoginUiEvent.OnLoginClick -> loginUser()

            LoginUiEvent.OnMessageDisplayed -> _loginScreenUiState.update {
                it.copy(
                    snackBarMessage = null
                )
            }

            is LoginUiEvent.EmailEmptyStateChanged -> _loginScreenUiState.update {
                it.copy(
                    isEmailEmpty = event.isEmailEmpty
                )
            }

            is LoginUiEvent.PasswordEmptyStateChanged -> _loginScreenUiState.update {
                it.copy(
                    isPasswordEmpty = event.isPasswordEmpty
                )
            }

            is LoginUiEvent.EmailForResetPasswordEmptyStateChange -> _loginScreenUiState.update {
                it.copy(
                    isEmailForResetPasswordEmpty = event.isEmailForResetPasswordEmpty
                )
            }

            is LoginUiEvent.OnEmailForResetPasswordChange -> _loginScreenUiState.update {
                it.copy(
                    emailForResetPassword = event.emailForResetPassword
                )
            }

            LoginUiEvent.OnResetPasswordClick -> resetPassword()
            is LoginUiEvent.OnResetPasswordDialogStateChanged -> _loginScreenUiState.update {
                it.copy(
                    shouldShowResetPasswordDialog = event.shouldShowResetPasswordDialog
                )
            }
        }
    }

    private fun loginUser() {
        val email = loginScreenUiState.value.email
        val password = loginScreenUiState.value.password

        _loginScreenUiState.update {
            it.copy(
                isEmailEmpty = email.isBlank(),
                isPasswordEmpty = password.isBlank()
            )
        }

        if (loginScreenUiState.value.isEmailEmpty || loginScreenUiState.value.isPasswordEmpty) return

        _loginScreenUiState.update { uiState ->
            uiState.copy(
                loginLoading = true, snackBarMessage = null
            )
        }
        viewModelScope.launch {
            when (val result = authRepository.login(email, password)) {
                is Resource.Failure -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loginLoading = false, snackBarMessage = result.exception.message
                        )
                    }
                }

                Resource.Loading -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loginLoading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loginLoading = false, snackBarMessage = null, shouldNavigate = true
                        )
                    }
                }
            }
        }
    }

    private fun resetPassword() {

        val email = loginScreenUiState.value.emailForResetPassword

        _loginScreenUiState.update {
            it.copy(
                isEmailForResetPasswordEmpty = email.isBlank()
            )
        }

        if (loginScreenUiState.value.isEmailForResetPasswordEmpty) return

        _loginScreenUiState.update { uiState ->
            uiState.copy(
                resetPasswordLoading = true,
                snackBarMessage = null,
                shouldNavigate = false
            )
        }

        viewModelScope.launch {
            when (val result = authRepository.resetPassword(email)) {
                is Resource.Failure -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            resetPasswordLoading = false,
                            snackBarMessage = result.exception.message,
                            shouldShowResetPasswordDialog = false
                        )
                    }
                }
                Resource.Loading -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            resetPasswordLoading = true, snackBarMessage = null, shouldShowResetPasswordDialog = true
                        )
                    }
                }
                is Resource.Success -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            resetPasswordLoading = false,
                            snackBarMessage = "Password reset link sent successfully",
                            shouldShowResetPasswordDialog = false
                        )
                    }
                }
            }
        }
    }
}