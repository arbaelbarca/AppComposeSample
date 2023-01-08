package com.arbaelbarca.appcomposesample.data.repository.mapping

import com.arbaelbarca.appcomposesample.domain.model.Meals

fun List<Meals>?.mappingToUseCaseEntity(): List<Meals> {
    val newList: MutableList<Meals> = mutableListOf()

    this?.forEach {
        newList.add(
            Meals(
                id = it.id.toString(),
                strMeal = it.strMeal.toString(),
                it.strMealThumb.toString()
            )
        )
    }

    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }

}