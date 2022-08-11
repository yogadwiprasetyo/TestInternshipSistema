package com.yogaprasetyo.testinternshipsistema.core.data.source.remote

import com.yogaprasetyo.testinternshipsistema.core.MainDispatcherRule
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.network.ApiService
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsDetailItem
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.MealsListItem
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseDetailFood
import com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response.ResponseListSeafood
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoteDataSourceTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var apiService: ApiService
    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup() {
        apiService = FakeApiService()
        remoteDataSource = RemoteDataSource(apiService)
    }

    @Test
    fun `when getListSeafood Should Not Null`() = runTest {
        val expectedResponse = generateDummyResponseList()
        val actualResponse = apiService.getListSeafood("Seafood")

        assertNotNull(actualResponse)
        assertEquals(expectedResponse.meals?.size, actualResponse.meals?.size)
    }

    @Test
    fun `when getDetailFood Should Not Null`() = runTest {
        val expectedResponse = generateDummyResponseDetail()
        val actualResponse = apiService.getDetailFood("101")

        assertNotNull(actualResponse)
        assertEquals(expectedResponse.meals?.get(0)?.idMeal, actualResponse.meals?.get(0)?.idMeal)
    }

    @Test
    fun `when getListSeafood Empty Category`() {
        val messageException = "Category harus diisi!"
        val exception = assertThrows(Exception::class.java) {
            runTest { apiService.getListSeafood("") }
        }
        assertEquals(messageException, exception.message)
    }

    @Test
    fun `when getDetailFood Empty Id`() {
        val messageException = "Id tidak boleh kosong!"
        val exception = assertThrows(Exception::class.java) {
            runTest { apiService.getDetailFood("") }
        }
        assertEquals(messageException, exception.message)
    }

    private fun generateDummyResponseList(): ResponseListSeafood =
        ResponseListSeafood(
            meals = listOf(
                MealsListItem(idMeal = "001"),
                MealsListItem(idMeal = "002"),
                MealsListItem(idMeal = "003"),
                MealsListItem(idMeal = "004"),
                MealsListItem(idMeal = "005"),
            )
        )

    private fun generateDummyResponseDetail(): ResponseDetailFood =
        ResponseDetailFood(
            meals = listOf(
                MealsDetailItem(idMeal = "101", strMeal = "Indomie", strArea = "Indonesia")
            )
        )
}