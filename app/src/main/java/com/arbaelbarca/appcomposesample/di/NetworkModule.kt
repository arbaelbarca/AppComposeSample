package com.arbaelbarca.appcomposesample.di

import com.arbaelbarca.appcomposesample.data.source.remote.service.MealsService
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val networkModules = module {
    single { createOkHttp() }
    single { createRetrofit(get()) }
    single { createMealsService(get()) }
}

fun createOkHttp(): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()
}

fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://www.themealdb.com/api/json/v1/1/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()
}

fun createMealsService(retrofit: Retrofit): MealsService {
    return retrofit.create(MealsService::class.java)
}