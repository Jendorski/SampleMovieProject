package com.example.samplemoviesproject.di.module

import com.example.samplemoviesproject.data.remote.api.NetworkService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class SampleMoviesProjectApiModule {

    @Singleton
    @Provides
    fun provideRetrofitService(): NetworkService = Retrofit.Builder()
        .baseUrl(NetworkService.THE_MOVIES_DB_API_URL)
        .addConverterFactory(
            MoshiConverterFactory.create(
                Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
            )
        )
        .build()
        .create(NetworkService::class.java)
}
