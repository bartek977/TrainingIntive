package com.example.trainingintive.di

import com.example.trainingintive.MyApplication
import com.example.trainingintive.data.ApplicationDatabase
import com.example.trainingintive.providers.DatabaseProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(application: MyApplication) =
        DatabaseProvider.get(application.applicationContext)

    @Provides
    @Singleton
    fun provideDogImageUrlDao(
        applicationDatabase: ApplicationDatabase
    ) = applicationDatabase.dogImageUrlDao()
}
