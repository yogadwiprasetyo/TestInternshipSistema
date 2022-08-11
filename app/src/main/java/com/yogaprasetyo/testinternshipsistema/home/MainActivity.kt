package com.yogaprasetyo.testinternshipsistema.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.yogaprasetyo.testinternshipsistema.R
import com.yogaprasetyo.testinternshipsistema.core.data.Resource
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsListItem
import com.yogaprasetyo.testinternshipsistema.databinding.ActivityMainBinding
import com.yogaprasetyo.testinternshipsistema.detail.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModel()
    private lateinit var mainAdapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainAdapter = MainAdapter(::moveToDetail)
        mainViewModel.loadFoodsByCategory().observe(this, Observer(::showResponse))
        setupRecyclerView()
    }

    private fun moveToDetail(foodId: String) {
        val intent = Intent(this, DetailActivity::class.java)
            .putExtra(DetailActivity.EXTRA_ID, foodId)
        startActivity(intent)
    }

    private fun showResponse(response: Resource<List<MealsListItem?>?>) {
        when (response) {
            is Resource.Loading -> showLoading()
            is Resource.Error -> showErrorInfo()
            is Resource.Success -> showFoods(response.data)
        }
    }

    private fun showLoading(isShow: Boolean = true) {
        binding.pb.isVisible = isShow
    }

    private fun showErrorInfo() {
        showLoading(false)
        val message = getString(R.string.general_error)
        showingInfo(message, true)
    }

    private fun showFoods(data: List<MealsListItem?>?) {
        showLoading(false)
        if (data.isNullOrEmpty()) {
            showEmptyInfo()
            return
        }

        mainAdapter.submitList(data)
    }

    private fun showEmptyInfo() {
        val message = getString(R.string.main_empty_list)
        showingInfo(message, true)
    }

    private fun showingInfo(message: String, isShow: Boolean) {
        binding.tvInfo.text = message
        binding.tvInfo.isVisible = isShow
        binding.rvFoods.isVisible = !isShow
    }

    private fun setupRecyclerView() {
        binding.rvFoods.apply {
            layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            adapter = mainAdapter
        }
    }

}