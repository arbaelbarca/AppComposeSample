package com.arbaelbarca.appcomposesample.data.source

import com.arbaelbarca.appcomposesample.data.source.remote.response.MealsCategoryResponse

interface MealsDataSource {
    suspend fun getMealsByCategory(categorySearch: String): MealsCategoryResponse
}
