package com.zaid.zavi.feature_home.presentation.home_screen

sealed interface HomeScreenUiEvent {

    object OnAllProductsClick: HomeScreenUiEvent
    object OnKitchenProductsClick: HomeScreenUiEvent
    object OnRoomProductsClick: HomeScreenUiEvent
    object OnWashroomProductsClick: HomeScreenUiEvent
    object OnMessageDisplayed : HomeScreenUiEvent

}