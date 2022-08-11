package com.yogaprasetyo.testinternshipsistema.core.data

import com.yogaprasetyo.testinternshipsistema.core.MainDispatcherRule
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.RemoteDataSource
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.network.ApiResponse
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsDetailItem
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsListItem
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseDetailFood
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseListSeafood
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class FoodRepositoryTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val category = "Seafood"
    private val foodId = "5225"

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var foodRepository: FoodRepository

    @Before
    fun setup() {
        foodRepository = FoodRepository(remoteDataSource)
    }

    @Test
    fun `when getFoodByCategory Should Success and Not Null`() = runTest {
        val expectedResult = ApiResponse.Success(generateDummyResultList())

        `when`(remoteDataSource.getFoodByCategory(category)).thenReturn(flowOf(expectedResult))
        val actualResult = remoteDataSource.getFoodByCategory(category).first()

        verify(remoteDataSource).getFoodByCategory(category)
        assertNotNull(actualResult)
        assertTrue(actualResult is ApiResponse.Success)
    }

    @Test
    fun `when getFoodById Should Success and Not Null`() = runTest {
        val expectedResult = ApiResponse.Success(generateDummyResponseDetail())

        `when`(remoteDataSource.getFoodById(foodId)).thenReturn(flowOf(expectedResult))
        val actualResult = remoteDataSource.getFoodById(foodId).first()

        verify(remoteDataSource).getFoodById(foodId)
        assertNotNull(actualResult)
        assertTrue(actualResult is ApiResponse.Success)
    }

    private fun generateDummyResultList(): ResponseListSeafood =
        ResponseListSeafood(
            meals = listOf(
                MealsListItem(idMeal = "1"),
                MealsListItem(idMeal = "2"),
                MealsListItem(idMeal = "3"),
                MealsListItem(idMeal = "4"),
                MealsListItem(idMeal = "5"),
            )
        )

    private fun generateDummyResponseDetail(): ResponseDetailFood =
        ResponseDetailFood(
            meals = listOf(
                MealsDetailItem(idMeal = "5225", strMeal = "Pizza", strArea = "Italian")
            )
        )
}