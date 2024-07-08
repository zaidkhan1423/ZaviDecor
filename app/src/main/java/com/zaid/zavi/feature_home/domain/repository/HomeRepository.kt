package com.zaid.zavi.feature_home.domain.repository

import com.google.firebase.firestore.QuerySnapshot
import com.zaid.zavi.core.utils.Resource

interface HomeRepository {

    suspend fun getSpecialProducts(): Resource<QuerySnapshot>

}