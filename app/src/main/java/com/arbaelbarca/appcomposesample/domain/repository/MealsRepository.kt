package com.arbaelbarca.appcomposesample.domain.repository

import com.arbaelbarca.appcomposesample.data.source.remote.response.MealsCategoryResponse
import com.arbaelbarca.appcomposesample.domain.model.Meals
import kotlinx.coroutines.flow.Flow

interface MealsRepository {
    fun getMealsByCategory(categorySearch: String): Flow<List<Meals>>
}