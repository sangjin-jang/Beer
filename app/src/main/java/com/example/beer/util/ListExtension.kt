package com.example.beer.util

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<List<T>?>.addToNotify(list: List<T>?) {
    this.value = this.value?.toMutableList()?.apply {
        addAll(list ?: emptyList())
    }

}