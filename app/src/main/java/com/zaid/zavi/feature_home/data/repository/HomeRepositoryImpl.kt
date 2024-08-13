package com.zaid.zavi.feature_home.data.repository

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.zaid.zavi.core.utils.Constants.PRODUCTS
import com.zaid.zavi.core.utils.Resource
import com.zaid.zavi.core.utils.firebase_utils.await
import com.zaid.zavi.feature_home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val firestore: FirebaseFirestore
) : HomeRepository {
    override suspend fun getAllProducts(): Resource<QuerySnapshot> {
        return try {
            val result =
                firestore.collection(PRODUCTS).get()
                    .await()
            Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun getKitchenProducts(): Resource<QuerySnapshot> {
        return try {
            val result =
                firestore.collection(PRODUCTS).whereEqualTo("category", "Kitchen").get()
                    .await()
            Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun getRoomProducts(): Resource<QuerySnapshot> {
        return try {
            val result =
                firestore.collection(PRODUCTS).whereEqualTo("category", "Room").get()
                    .await()
            Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun getWashroomProducts(): Resource<QuerySnapshot> {
        return try {
            val result =
                firestore.collection(PRODUCTS).whereEqualTo("category", "Washroom").get()
                    .await()
            Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }

    override suspend fun getPopularProducts(): Resource<QuerySnapshot> {
        return try {
            val result =
                firestore.collection(PRODUCTS).whereEqualTo("category", "Popular").get()
                    .await()
            Resource.Success(result)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Failure(e)
        }
    }
}