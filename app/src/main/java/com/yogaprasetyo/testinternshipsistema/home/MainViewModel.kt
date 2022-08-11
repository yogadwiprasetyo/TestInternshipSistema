package com.yogaprasetyo.testinternshipsistema.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yogaprasetyo.testinternshipsistema.core.data.FoodRepository

class MainViewModel(private val repository: FoodRepository) : ViewModel() {
    fun loadFoodsByCategory() = repository.getFoodByCategory().asLiveData()
}