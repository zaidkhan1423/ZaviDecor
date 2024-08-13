package com.zaid.zavi.feature_home.domain.repository

import com.google.firebase.firestore.QuerySnapshot
import com.zaid.zavi.core.utils.Resource

interface HomeRepository {

    suspend fun getPopularProducts(): Resource<QuerySnapshot>
    suspend fun getAllProducts(): Resource<QuerySnapshot>
    suspend fun getKitchenProducts(): Resource<QuerySnapshot>
    suspend fun getRoomProducts(): Resource<QuerySnapshot>
    suspend fun getWashroomProducts(): Resource<QuerySnapshot>

}