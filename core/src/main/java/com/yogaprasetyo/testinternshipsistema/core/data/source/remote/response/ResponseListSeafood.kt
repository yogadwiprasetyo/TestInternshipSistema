package com.yogaprasetyo.testinternshipsistema.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseListSeafood(

	@field:SerializedName("meals")
	val meals: List<MealsListItem?>? = null
)

data class MealsListItem(

	@field:SerializedName("strMealThumb")
	val strMealThumb: String? = null,

	@field:SerializedName("idMeal")
	val idMeal: String? = null,

	@field:SerializedName("strMeal")
	val strMeal: String? = null
)
