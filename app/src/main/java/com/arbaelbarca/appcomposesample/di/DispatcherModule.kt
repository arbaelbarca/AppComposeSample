package com.arbaelbarca.appcomposesample.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single(named(MealsDispatcher.IO)) {
        Dispatchers.IO
    }

    single(named(MealsDispatcher.Main)) {
        Dispatchers.Main
    }

    single(named(MealsDispatcher.Default)) {
        Dispatchers.Default
    }
}

object MealsDispatcher {
    const val IO = "io"
    const val Main = "io"
    const val Default = "io"
}