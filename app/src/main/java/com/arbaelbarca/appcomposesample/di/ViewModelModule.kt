package com.arbaelbarca.appcomposesample.di

import com.arbaelbarca.appcomposesample.ui.presentation.ViewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel {
        MainViewModel(get())
    }
}