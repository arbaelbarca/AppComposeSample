package com.arbaelbarca.appcomposesample.domain.usecase

import com.arbaelbarca.appcomposesample.domain.model.Meals
import com.arbaelbarca.appcomposesample.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class GetMealsByCategoryUseCase(
    val mealsRepository: MealsRepository
) {
    operator fun invoke(category: String) =
        mealsRepository.getMealsByCategory(categorySearch = category)
}