package com.example.beer.data.model

data class BeerResponse (
    val id: Int,
    val name: String?,
    val tagline: String?,
    val description: String?,
    val imageUrl: String?
        )