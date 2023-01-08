package com.arbaelbarca.appcomposesample.data.source.remote.service

import com.arbaelbarca.appcomposesample.data.source.remote.response.MealsCategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsService {

    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): MealsCategoryResponse
}