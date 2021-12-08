package com.example.beer.view.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.beer.BR
import com.example.beer.data.Beer
import com.example.beer.databinding.ItemBeerBinding

class BeerViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(beer: Beer) {
        binding.setVariable(BR.item, beer)
        binding.executePendingBindings()
    }
}