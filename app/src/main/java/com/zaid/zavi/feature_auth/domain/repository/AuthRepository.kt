package com.zaid.zavi.feature_auth.domain.repository

import com.google.firebase.auth.FirebaseUser
import com.zaid.zavi.core.utils.Resource

interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Resource<FirebaseUser>
    suspend fun signup(name: String, email: String,password: String): Resource<FirebaseUser>
    suspend fun resetPassword(email: String): Resource<Void>
    fun logout()
}