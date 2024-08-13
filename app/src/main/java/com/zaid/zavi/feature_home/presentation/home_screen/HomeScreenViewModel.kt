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
        getAllProducts()
        getPopularProducts()
    }

    fun onEvent(event: HomeScreenUiEvent) {
        when (event) {
            HomeScreenUiEvent.OnAllProductsClick -> getAllProducts()
            HomeScreenUiEvent.OnMessageDisplayed -> _homeScreenUiState.update {
                it.copy(
                    snackBarMessage = null
                )
            }

            HomeScreenUiEvent.OnKitchenProductsClick -> getKitchenProducts()
            HomeScreenUiEvent.OnRoomProductsClick -> getRoomProducts()
            HomeScreenUiEvent.OnWashroomProductsClick -> getWashroomProducts()
        }
    }

    private fun getAllProducts() {

        _homeScreenUiState.update { uiState ->
            uiState.copy(
                productsLoading = true, snackBarMessage = null
            )
        }

        viewModelScope.launch {
            when (val result = homeRepository.getAllProducts()) {
                is Resource.Failure -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = false, snackBarMessage = result.exception.message
                        )
                    }
                }

                Resource.Loading -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    val specialProducts = result.result.toObjects(Products::class.java)
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = false,
                            snackBarMessage = "Data Fetch",
                            products = specialProducts
                        )
                    }
                }
            }
        }
    }
    private fun getKitchenProducts() {

        _homeScreenUiState.update { uiState ->
            uiState.copy(
                productsLoading = true, snackBarMessage = null
            )
        }

        viewModelScope.launch {
            when (val result = homeRepository.getKitchenProducts()) {
                is Resource.Failure -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = false, snackBarMessage = result.exception.message
                        )
                    }
                }

                Resource.Loading -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    val kitchenProducts = result.result.toObjects(Products::class.java)
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = false,
                            snackBarMessage = "Data Fetch",
                            products = kitchenProducts
                        )
                    }
                }
            }
        }
    }
    private fun getRoomProducts() {

        _homeScreenUiState.update { uiState ->
            uiState.copy(
                productsLoading = true, snackBarMessage = null
            )
        }

        viewModelScope.launch {
            when (val result = homeRepository.getRoomProducts()) {
                is Resource.Failure -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = false, snackBarMessage = result.exception.message
                        )
                    }
                }

                Resource.Loading -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    val roomProducts = result.result.toObjects(Products::class.java)
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = false,
                            snackBarMessage = "Data Fetch",
                            products = roomProducts
                        )
                    }
                }
            }
        }
    }
    private fun getWashroomProducts() {

        _homeScreenUiState.update { uiState ->
            uiState.copy(
                productsLoading = true, snackBarMessage = null
            )
        }

        viewModelScope.launch {
            when (val result = homeRepository.getWashroomProducts()) {
                is Resource.Failure -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = false, snackBarMessage = result.exception.message
                        )
                    }
                }

                Resource.Loading -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    val washroomProducts = result.result.toObjects(Products::class.java)
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            productsLoading = false,
                            snackBarMessage = "Data Fetch",
                            products = washroomProducts
                        )
                    }
                }
            }
        }
    }

    private fun getPopularProducts() {

        _homeScreenUiState.update { uiState ->
            uiState.copy(
                popularProductsLoading = true, snackBarMessage = null
            )
        }

        viewModelScope.launch {
            when (val result = homeRepository.getPopularProducts()) {
                is Resource.Failure -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            popularProductsLoading = false, snackBarMessage = result.exception.message
                        )
                    }
                }

                Resource.Loading -> {
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            popularProductsLoading = true, snackBarMessage = null
                        )
                    }
                }

                is Resource.Success -> {
                    val popularProducts = result.result.toObjects(Products::class.java)
                    _homeScreenUiState.update { uiState ->
                        uiState.copy(
                            popularProductsLoading = false,
                            snackBarMessage = "Data Fetch",
                            popularProducts = popularProducts
                        )
                    }
                }
            }
        }
    }
}