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

    fun loginUser(email: String, password: String) {
        _loginScreenUiState.update { uiState ->
            uiState.copy(
                loading = true, snackBarMessage = null
            )
        }
        viewModelScope.launch {
            when (val result = authRepository.login(email, password)) {
                is Resource.Failure -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = false, snackBarMessage = result.exception.message
                        )
                    }
                }

                Resource.Loading -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = false, snackBarMessage = null, shouldNavigate = true
                        )
                    }
                }
            }
        }
    }

    fun resetPassword(email: String) {
        _loginScreenUiState.update { uiState ->
            uiState.copy(
                loading = true, snackBarMessage = null, shouldNavigate = false
            )
        }
        viewModelScope.launch {
            when (val result = authRepository.resetPassword(email)) {
                is Resource.Failure -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = false,
                            snackBarMessage = result.exception.message,
                            shouldNavigate = false
                        )
                    }
                }

                Resource.Loading -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = true, snackBarMessage = null, shouldNavigate = false
                        )
                    }
                }

                is Resource.Success -> {
                    _loginScreenUiState.update { uiState ->
                        uiState.copy(
                            loading = false,
                            snackBarMessage = "Password reset link sent successfully",
                            shouldNavigate = false
                        )
                    }
                }
            }
        }
    }

}