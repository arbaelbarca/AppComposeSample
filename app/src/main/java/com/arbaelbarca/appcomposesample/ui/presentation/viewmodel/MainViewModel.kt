package com.arbaelbarca.appcomposesample.ui.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arbaelbarca.appcomposesample.domain.usecase.GetMealsByCategoryUseCase
import com.arbaelbarca.appcomposesample.ui.presentation.model.MainEventState
import com.arbaelbarca.appcomposesample.ui.presentation.model.MainUiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(
    val getMealsByCategoryUseCase: GetMealsByCategoryUseCase
) : ViewModel() {

    var search by mutableStateOf("")
        private set

    val getuiState: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.Idle)

    val uiState get() = getuiState.asStateFlow()

    fun onEvent(event: MainEventState) {
        when (event) {
            is MainEventState.SearchOnChange -> {
                search = event.search
            }

            MainEventState.Search -> {
                getMealsByCateogry()
            }
        }
    }

    fun getMealsByCateogry() {
        viewModelScope.launch {
            getMealsByCategoryUseCase.invoke(
                category = search
            ).onStart {
                getuiState.update {
                    MainUiState.Loading
                }
            }.catch {
                getuiState.update {
                    MainUiState.Error(
                        "error"
                    )
                }
            }.collectLatest { dataList ->
                if (dataList.isNullOrEmpty()) {
                    getuiState.update {
                        MainUiState.Empty
                    }
                } else {
                    getuiState.update {
                        MainUiState.Success(
                            data = dataList
                        )
                    }
                }

            }
        }
    }
}