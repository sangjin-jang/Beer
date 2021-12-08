package com.example.beer.view.main

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beer.data.Beer
import com.example.beer.repository.BeerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val beerRepository: BeerRepository) :
    ViewModel() {

    var beerName: String? = null
    private var page: Int = 1
    private val perPage: Int = 10

    val beers = MutableLiveData<List<Beer>?>()
    val isEmpty = ObservableBoolean(false)
    var hasError = false
    var endOfPage = false

    init {
        getInitBeers()
    }

    fun getInitBeers() {
        page = 1
        endOfPage = false

        viewModelScope.launch {
            beerRepository.getBeers(beerName, page, perPage).runCatching {
                beers.value = this.body()
                isEmpty.set(this.body().isNullOrEmpty())
                endOfPage = this.body().isNullOrEmpty()
                hasError = false
            }.onFailure {
                hasError = true
            }
        }
    }

    fun getMoreBeers() {
        if (endOfPage) return

        page++

        viewModelScope.launch {
            beerRepository.getBeers(beerName, page, perPage).runCatching {
                this.body()?.let {
                    val newBeers = beers.value?.toMutableList()
                    newBeers?.addAll(it)
                    beers.value = newBeers
                }

                hasError = false
                endOfPage = this.body().isNullOrEmpty()
            }.onFailure {
                hasError = true
            }
        }
    }
}