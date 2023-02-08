package com.arbaelbarca.appcomposesample.domain.model

import android.os.Parcelable
import java.io.Serializable


data class Meals(
    val id: String,
    val strMeal: String,
    val strMealThumb: String
) : Serializable