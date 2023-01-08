package com.arbaelbarca.appcomposesample.data.repository

import com.arbaelbarca.appcomposesample.data.repository.mapping.mappingToUseCaseEntity
import com.arbaelbarca.appcomposesample.data.source.MealsDataSource
import com.arbaelbarca.appcomposesample.data.source.remote.response.MealsCategoryResponse
import com.arbaelbarca.appcomposesample.domain.model.Meals
import com.arbaelbarca.appcomposesample.domain.repository.MealsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MealsRepositoryImpl(
    val mealsDataSource: MealsDataSource,
    val dispatcherMeals: CoroutineDispatcher
) : MealsRepository {
    override fun getMealsByCategory(categorySearch: String): Flow<List<Meals>> {

        return flow {
            val listMeals =
                mealsDataSource.getMealsByCategory(categorySearch = categorySearch).meals

            val newList: MutableList<Meals> = mutableListOf()

            listMeals?.forEach {
                newList.add(
                    Meals(
                        id = it?.idMeal.toString(),
                        strMeal = it?.strMeal.toString(),
                        it?.strMealThumb.toString()
                    )
                )
            }
            emit(newList)
        }.flowOn(dispatcherMeals)
    }
}