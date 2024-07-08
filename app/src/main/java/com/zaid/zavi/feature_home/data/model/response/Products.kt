package com.zaid.zavi.feature_home.data.model.response

data class Products(
    val id: String,
    val name: String,
    val category: String,
    val price: Float,
    val offerPercentage: Float? = null,
    val description: String? = null,
    val colors: List<String>? = null,
    val sizes: List<String>? = null,
    val images: List<String>
){
    constructor(): this("","","",0f, images = emptyList())
}