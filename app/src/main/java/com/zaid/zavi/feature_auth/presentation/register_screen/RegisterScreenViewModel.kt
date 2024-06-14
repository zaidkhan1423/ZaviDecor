package com.zaid.zavi.feature_auth.presentation.register_screen

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
class RegisterScreenViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerScreenUiState = MutableStateFlow(RegisterScreenUiState())
    val registerScreenUiState = _registerScreenUiState.asStateFlow()

    fun onEvent(event: RegisterScreenUiEvent) {
        when (event) {
            is RegisterScreenUiEvent.OnConfirmPasswordChange -> _registerScreenUiState.update {
                it.copy(
                    confirmPassword = event.confirmPassword
                )
            }

            is RegisterScreenUiEvent.OnEmailChange -> _registerScreenUiState.update {
                it.copy(
                    email = event.email
                )
            }

            RegisterScreenUiEvent.OnMessageDisplayed -> _registerScreenUiState.update {
                it.copy(
                    snackBarMessage = null
                )
            }

            is RegisterScreenUiEvent.OnNameChange -> _registerScreenUiState.update {
                it.copy(
                    name = event.name
                )
            }

            is RegisterScreenUiEvent.OnPasswordChange -> _registerScreenUiState.update {
                it.copy(
                    password = event.password
                )
            }

            RegisterScreenUiEvent.OnRegisterUserClick -> signupUser()

            is RegisterScreenUiEvent.EmailEmptyStateChanged -> _registerScreenUiState.update {
                it.copy(
                    isEmailBlank = event.isEmailEmpty
                )
            }

            is RegisterScreenUiEvent.NameEmptyStateChanged -> _registerScreenUiState.update {
                it.copy(
                    isNameBlank = event.isNameEmpty
                )
            }

            is RegisterScreenUiEvent.PasswordEmptyStateChanged -> _registerScreenUiState.update {
                it.copy(
                    isPasswordBlank = event.isPasswordEmpty
                )
            }

            is RegisterScreenUiEvent.PasswordMatchStateChanged -> _registerScreenUiState.update {
                it.copy(
                    isPasswordBlank = event.isPasswordMatch
                )
            }
        }
    }

    private fun signupUser() {

        val name = registerScreenUiState.value.name
        val email = registerScreenUiState.value.email
        val password = registerScreenUiState.value.password
        val confirmPassword = registerScreenUiState.value.confirmPassword

        _registerScreenUiState.update {
            it.copy(
                isNameBlank = name.isEmpty(),
                isEmailBlank = email.isBlank(),
                isPasswordBlank = password.isBlank(),
                isPasswordMatch = password == confirmPassword
            )
        }

        if (!registerScreenUiState.value.isPasswordMatch || registerScreenUiState.value.isNameBlank || registerScreenUiState.value.isEmailBlank || registerScreenUiState.value.isPasswordBlank) return


        _registerScreenUiState.update { uiState ->
            uiState.copy(
                loading = true, snackBarMessage = null
            )
        }
        viewModelScope.launch {
            when (val result = authRepository.signup(name, email, password)) {
                is Resource.Failure -> {
                    _registerScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = false, snackBarMessage = result.exception.message
                        )
                    }
                }
                is Resource.Loading -> {
                    _registerScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    _registerScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = false,
                            snackBarMessage = "Account has been created now you can Login",
                            shouldNavigate = true
                        )
                    }
                }
            }
            authRepository.logout()
        }
    }

}