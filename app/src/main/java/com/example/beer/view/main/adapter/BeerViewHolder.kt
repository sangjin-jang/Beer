package com.example.beer.view.main.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.beer.BR
import com.example.beer.data.Beer
import com.example.beer.databinding.ItemBeerBinding
import com.example.beer.util.DataBindingPresenter

class BeerViewHolder(private val binding: ItemBeerBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(beer: Beer, presenter: DataBindingPresenter) {
        binding.setVariable(BR.item, beer)
        binding.setVariable(BR.presenter, presenter)
        binding.executePendingBindings()
    }
}