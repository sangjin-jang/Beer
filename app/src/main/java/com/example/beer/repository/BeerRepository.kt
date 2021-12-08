package com.example.beer.repository

import com.example.beer.service.ApiService
import javax.inject.Inject

class BeerRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getBeers(query: String?, page: Int, perPage: Int) =
        apiService.getBeers(query = query, page = page, perPage = perPage)
}