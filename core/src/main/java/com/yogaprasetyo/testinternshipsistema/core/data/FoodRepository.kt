package com.yogaprasetyo.testinternshipsistema.core.data

import android.util.Log
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.RemoteDataSource
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.network.ApiResponse
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsDetailItem
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FoodRepository(private val remoteDataSource: RemoteDataSource) {

    fun getFoodByCategory(): Flow<Resource<List<MealsListItem?>?>> =
        flow {
            emit(Resource.Loading())
            try {
                remoteDataSource.getFoodByCategory(CATEGORY).collect {
                    val result = handleStateAPI(it)
                    emit(Resource.Success(result.data?.meals))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
                Log.d(TAG, e.message.toString())
            }
        }

    fun getFoodById(foodId: String): Flow<Resource<MealsDetailItem?>> =
        flow {
            emit(Resource.Loading())
            try {
                remoteDataSource.getFoodById(foodId).collect {
                    val result = handleStateAPI(it)
                    emit(Resource.Success(result.data?.meals?.get(0)))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message.toString()))
                Log.d(TAG, e.message.toString())
            }
        }

    private fun <ResponseType> handleStateAPI(apiResponse: ApiResponse<ResponseType>): Resource<ResponseType> =
        when (apiResponse) {
            is ApiResponse.Success -> Resource.Success(apiResponse.data)
            is ApiResponse.Error -> Resource.Error(apiResponse.errorMessage)
            is ApiResponse.Empty -> Resource.Loading()
        }

    companion object {
        const val TAG = "FoodRepository"
        const val CATEGORY = "Seafood"
    }
}