package com.example.beer.view.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.beer.R
import com.example.beer.binding.SimpleItemDiffCallback
import com.example.beer.data.model.Beer
import com.example.beer.databinding.ItemBeerBinding
import com.example.beer.util.DataBindingPresenter

class BeerAdapter(private val presenter: DataBindingPresenter) :
    ListAdapter<Beer, BeerViewHolder>(SimpleItemDiffCallback<Beer>()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BeerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemBeerBinding>(
                layoutInflater,
                R.layout.item_beer,
                parent,
                false
            )
        return BeerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BeerViewHolder, position: Int) {
        holder.bind(getItem(position), presenter)
    }
}