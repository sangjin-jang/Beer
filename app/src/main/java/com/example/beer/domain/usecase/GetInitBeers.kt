package com.example.beer.domain.usecase

import com.example.beer.data.model.Beer
import com.example.beer.domain.repository.BeerRepository
import javax.inject.Inject
import com.example.beer.domain.usecase.GetInitBeers.Params

class GetInitBeers @Inject constructor(private val beerRepository: BeerRepository) :
    BaseUseCase<List<Beer>, Params>() {

    override suspend fun run(params: Params): List<Beer>? =
        beerRepository.getBeers(query = params.query, page = 1, perPage = 10)

    data class Params(val query: String?)
}