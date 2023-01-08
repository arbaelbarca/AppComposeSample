package com.arbaelbarca.appcomposesample.data.source

import com.arbaelbarca.appcomposesample.data.source.remote.response.MealsCategoryResponse
import com.arbaelbarca.appcomposesample.data.source.remote.service.MealsService

class MealsDataSourceImpl(
    val mealsService: MealsService
) : MealsDataSource {
    override suspend fun getMealsByCategory(categorySearch: String): MealsCategoryResponse {
        return mealsService.getMealsByCategory(categorySearch)
    }

}