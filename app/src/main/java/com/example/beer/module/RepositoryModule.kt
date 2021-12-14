package com.example.beer.module

import com.example.beer.data.api.ApiService
import com.example.beer.domain.repository.BeerRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideBeersRepository(apiService: ApiService) = BeerRepository(apiService)
}