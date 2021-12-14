package com.example.beer.data.mapper

import com.example.beer.data.model.Beer
import com.example.beer.data.model.BeerResponse

fun BeerResponse.toModelL(): Beer = Beer(id, name, tagline, description, imageUrl)

fun List<BeerResponse>.toListModel(): List<Beer> {
    return map {
        it.toModelL()
    }
}