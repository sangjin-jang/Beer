package com.example.beer.domain.usecase

import com.example.beer.data.model.Beer
import com.example.beer.domain.repository.BeerRepository
import com.example.beer.domain.usecase.BeerUseCase.Params
import javax.inject.Inject

class BeerUseCase @Inject constructor(private val beerRepository: BeerRepository) :
    BaseUseCase<List<Beer>, Params>() {

    override suspend fun run(params: Params): List<Beer>? =
        beerRepository.getBeers(params.query, params.page, params.perPage)

    data class Params(
        val query: String?,
        val page: Int,
        val perPage: Int
    )
}