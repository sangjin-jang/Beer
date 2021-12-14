package com.example.beer.domain.repository

import com.example.beer.data.api.ApiService
import com.example.beer.data.mapper.toListModel
import javax.inject.Inject

class BeerRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getBeers(query: String?, page: Int, perPage: Int) =
        apiService.getBeers(query, page, perPage).body()?.toListModel()

    suspend fun getBeerDetail(id: Int) =
        apiService.getBeerDetail(id = id)
}