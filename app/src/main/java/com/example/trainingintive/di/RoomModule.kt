package com.example.trainingintive.di

import android.content.Context
import com.example.trainingintive.data.ApplicationDatabase
import com.example.trainingintive.providers.DatabaseProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        DatabaseProvider.get(context)

    @Provides
    @Singleton
    fun provideDogImageUrlDao(
        applicationDatabase: ApplicationDatabase
    ) = applicationDatabase.dogImageUrlDao()
}
