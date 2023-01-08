package com.arbaelbarca.appcomposesample.di

import com.arbaelbarca.appcomposesample.data.repository.MealsRepositoryImpl
import com.arbaelbarca.appcomposesample.domain.repository.MealsRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module


val repositoryModule = module {
    single<MealsRepository> {
        MealsRepositoryImpl(
            get(),
            get(named(MealsDispatcher.IO))
        )
    }

}