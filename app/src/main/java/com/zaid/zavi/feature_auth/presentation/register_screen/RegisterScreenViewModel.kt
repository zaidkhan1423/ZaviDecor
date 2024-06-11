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

    fun signupUser(name: String, email: String, password: String) {
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