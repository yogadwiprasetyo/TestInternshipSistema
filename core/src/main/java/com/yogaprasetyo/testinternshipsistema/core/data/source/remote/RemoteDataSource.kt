package com.yogaprasetyo.testinternshipsistema.core.data.source.remote

import android.util.Log
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.network.ApiResponse
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.network.ApiService
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseDetailFood
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseListSeafood
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getFoodByCategory(category: String): Flow<ApiResponse<ResponseListSeafood>> =
        flow {
            try {
                val response = apiService.getListSeafood(category)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.d(TAG, e.message.toString())
                e.printStackTrace()
            }
        }

    suspend fun getFoodById(foodId: String): Flow<ApiResponse<ResponseDetailFood>> =
        flow {
            try {
                val response = apiService.getDetailFood(foodId)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.message.toString()))
                Log.d(TAG, e.message.toString())
                e.printStackTrace()
            }
        }

    companion object {
        const val TAG = "RemoteDataSource"
    }
}