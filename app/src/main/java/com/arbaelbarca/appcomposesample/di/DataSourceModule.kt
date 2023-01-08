package com.arbaelbarca.appcomposesample.di

import com.arbaelbarca.appcomposesample.data.source.MealsDataSource
import com.arbaelbarca.appcomposesample.data.source.MealsDataSourceImpl
import org.koin.dsl.module


val dataSourceModule = module {
    single<MealsDataSource> { MealsDataSourceImpl(get()) }
}