package com.arbaelbarca.appcomposesample

import android.app.Application
import com.arbaelbarca.appcomposesample.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MainApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApp)
            // Load modules
            modules(
                listOf(
                    dataSourceModule,
                    dispatcherModule,
                    networkModules,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}