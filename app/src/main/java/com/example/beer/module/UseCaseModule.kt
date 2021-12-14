package com.example.beer.module

import com.example.beer.domain.repository.BeerRepository
import com.example.beer.domain.usecase.BeerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
    @Singleton
    @Provides
    fun provideBeerUseCase(beerRepository: BeerRepository) = BeerUseCase(beerRepository)
}