package com.arbaelbarca.appcomposesample.ui.presentation.model

import com.arbaelbarca.appcomposesample.data.source.remote.response.MealsCategoryResponse
import com.arbaelbarca.appcomposesample.domain.model.Meals

sealed interface MainUiState {
    object Idle : MainUiState
    object Loading : MainUiState
    object Empty : MainUiState

    data class Success(val data: List<Meals>) : MainUiState
    data class Error(val error: String) : MainUiState
}
