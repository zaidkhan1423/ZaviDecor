package com.zaid.zavi.feature_home.di

import com.zaid.zavi.feature_home.data.repository.HomeRepositoryImpl
import com.zaid.zavi.feature_home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {
    @Provides
    fun providesHomeRepository(homeRepositoryImpl: HomeRepositoryImpl): HomeRepository = homeRepositoryImpl
}
