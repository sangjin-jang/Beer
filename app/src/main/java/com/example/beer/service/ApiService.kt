package com.example.beer.service

import com.example.beer.data.Beer
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
    ): Response<ArrayList<Beer>>

    @GET("beers/random")
    suspend fun randomBeer(): Response<Beer>

    @GET("beers/{id}")
    suspend fun getBeerDetail(@Path("id") id: Int): Response<Beer>
}