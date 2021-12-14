package com.example.beer.data.api

import com.example.beer.data.model.Beer
import com.example.beer.data.model.BeerResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("beers")
    suspend fun getBeers(
        @Query("beer_name") query: String?,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Response<List<BeerResponse>>

    @GET("beers/random")
    suspend fun randomBeer(): Response<Beer>

    @GET("beers/{id}")
    suspend fun getBeerDetail(@Path("id") id: Int): Response<Beer>
}