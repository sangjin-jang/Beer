package com.example.beer.view.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.beer.R
import com.example.beer.data.Beer
import com.example.beer.databinding.ActivityMainBinding
import com.example.beer.util.DataBindingPresenter
import com.example.beer.view.main.adapter.BeerAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val beerAdapter by lazy {
        BeerAdapter(object : DataBindingPresenter {
            override fun onClick(view: View, item: Any) {
                if (item is Beer) {
                    Toast.makeText(this@MainActivity, "${item.id}", Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
            .apply {
                lifecycleOwner = this@MainActivity
                viewModel = mainViewModel
            }.also {
                binding = it
            }

        initView()
        initObserver()
    }

    private fun initView() {
        binding.rvBeers.apply {
            adapter = beerAdapter
            layoutManager = LinearLayoutManager(context)

            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    (layoutManager as? LinearLayoutManager)?.run {
                        if (findLastCompletelyVisibleItemPosition() >= itemCount - 1) {
                            mainViewModel.getMoreBeers()
                        }
                    }
                }
            })
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                mainViewModel.getInitBeers()
                return true
            }

            override fun onQueryTextChange(text: String?): Boolean {
                mainViewModel.beerName = text
                return true
            }
        })
    }

    private fun initObserver() {
        mainViewModel.beers.observe(this, {
            beerAdapter.submitList(it)
        })
    }
}