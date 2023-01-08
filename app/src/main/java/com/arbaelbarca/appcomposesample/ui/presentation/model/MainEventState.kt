package com.arbaelbarca.appcomposesample.ui.presentation.model

sealed class MainEventState {
    data class SearchOnChange(val search: String) : MainEventState()

    object Search : MainEventState()
}
