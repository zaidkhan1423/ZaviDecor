package com.zaid.zavi.feature_auth.di

import com.zaid.zavi.feature_auth.data.repository.AuthRepositoryImpl
import com.zaid.zavi.feature_auth.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    fun providesAuthRepository(authRepositoryImpl: AuthRepositoryImpl): AuthRepository = authRepositoryImpl
}