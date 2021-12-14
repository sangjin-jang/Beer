package com.example.beer.view.main

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beer.data.model.Beer
import com.example.beer.domain.usecase.GetInitBeers
import com.example.beer.domain.usecase.GetMoreBeers
import com.example.beer.util.addToNotify
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getInitBeers: GetInitBeers,
    private val getMoreBeers: GetMoreBeers
) :
    ViewModel() {

    var beerName: String? = null
    private var page: Int = 1
    private val perPage: Int = 10

    private val _beers = MutableLiveData<List<Beer>?>()
    val beers: LiveData<List<Beer>?> = _beers

    var isEmpty = ObservableBoolean(false)
    var hasError = ObservableBoolean(false)
    var endOfPage = false

    init {
        getInitBeers()
    }

    fun getInitBeers() {
        page = 1
        endOfPage = false

        runCatching {
            getInitBeers(viewModelScope, GetInitBeers.Params(beerName)) {
                _beers.value = it
                isEmpty.set(it.isNullOrEmpty())
                endOfPage = it.isNullOrEmpty()
            }
        }.onSuccess {
            hasError.set(false)
        }.onFailure {
            hasError.set(true)
        }
    }

    fun getMoreBeers() {
        if (endOfPage) return

        runCatching {
            getMoreBeers(viewModelScope, GetMoreBeers.Params(beerName, ++page, perPage)) {
                _beers.addToNotify(it)
                endOfPage = it.isNullOrEmpty()
            }
        }.onSuccess {
            hasError.set(false)
        }.onFailure {
            hasError.set(true)
        }
    }
}