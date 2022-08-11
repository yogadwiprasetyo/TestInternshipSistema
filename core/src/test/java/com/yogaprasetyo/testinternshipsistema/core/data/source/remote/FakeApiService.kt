package com.yogaprasetyo.testinternshipsistema.core.data.source.remote

import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.network.ApiService
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsDetailItem
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsListItem
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseDetailFood
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseListSeafood

class FakeApiService : ApiService {
    override suspend fun getListSeafood(category: String): ResponseListSeafood {
        return if (category.isEmpty()) {
            throw Exception("Category harus diisi!")
        } else {
            ResponseListSeafood(
                meals = listOf(
                    MealsListItem(idMeal = "001"),
                    MealsListItem(idMeal = "002"),
                    MealsListItem(idMeal = "003"),
                    MealsListItem(idMeal = "004"),
                    MealsListItem(idMeal = "005"),
                )
            )
        }
    }

    override suspend fun getDetailFood(foodId: String): ResponseDetailFood {
        return if (foodId.isEmpty()) {
            throw Exception("Id tidak boleh kosong!")
        } else {
            ResponseDetailFood(
                meals = listOf(
                    MealsDetailItem(idMeal = "101", strMeal = "Indomie", strArea = "Indonesia")
                )
            )
        }
    }
}