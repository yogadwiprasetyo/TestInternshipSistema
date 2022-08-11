package com.yogaprasetyo.testinternshipsistema.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.yogaprasetyo.testinternshipsistema.core.data.FoodRepository

class DetailViewModel(private val repository: FoodRepository) : ViewModel() {
    fun loadFood(foodId: String) = repository.getFoodById(foodId).asLiveData()
}