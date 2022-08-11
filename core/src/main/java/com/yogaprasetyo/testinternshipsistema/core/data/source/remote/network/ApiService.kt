package com.yogaprasetyo.testinternshipsistema.core.data.source.remote.network

import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseDetailFood
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseListSeafood
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("filter.php")
    suspend fun getListSeafood(
        @Query("c") category: String
    ): ResponseListSeafood

    @GET("lookup.php")
    suspend fun getDetailFood(
        @Query("i") foodId: String
    ): ResponseDetailFood
}