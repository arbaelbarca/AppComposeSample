package com.arbaelbarca.appcomposesample.di

import com.arbaelbarca.appcomposesample.domain.usecase.GetMealsByCategoryUseCase
import org.koin.dsl.module


val useCaseModule = module {
    factory {
        GetMealsByCategoryUseCase(get())
    }
}