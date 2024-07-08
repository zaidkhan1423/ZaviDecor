package com.zaid.zavi.feature_home.presentation.home_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaid.zavi.core.utils.Resource
import com.zaid.zavi.feature_home.data.model.response.Products
import com.zaid.zavi.feature_home.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {

    private val _homeScreenUiState = MutableStateFlow(HomeScreenUiState())
    val homeScreenUiState = _homeScreenUiState.asStateFlow()

    init {
        getSpecialProducts()
    }

    private fun getSpecialProducts() {

        _homeScreenUiState.update { uiState ->
            uiState.copy(
                loginLoading = true, snackBarMessage = null
            )
        }

        viewModelScope.launch {
            when (val result = homeRepository.getSpecialProducts()) {
                is Resource.Failure -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            loginLoading = false, snackBarMessage = result.exception.message
                        )
                    }
                }

                Resource.Loading -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            loginLoading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    val products = result.result.toObjects(Products::class.java)
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            loginLoading = false, snackBarMessage = null, products = products
                        )
                    }
                }
            }
        }
    }


}