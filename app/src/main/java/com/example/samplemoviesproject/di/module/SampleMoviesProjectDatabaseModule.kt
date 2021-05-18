package com.example.samplemoviesproject.di.module

import android.app.Application
import com.example.samplemoviesproject.data.local.SampleMoviesProjectDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class SampleMoviesProjectDatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(application: Application) = SampleMoviesProjectDatabase.getInstance(application)

    @Singleton
    @Provides
    fun providePostsDao(database: SampleMoviesProjectDatabase) = database.getMoviesDao()
}
