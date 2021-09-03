package com.example.trainingintive.di

import android.content.Context
import com.example.trainingintive.data.ApplicationDatabase
import com.example.trainingintive.providers.DatabaseProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context) =
        DatabaseProvider.get(context)

    @Provides
    @Singleton
    fun provideDogImageUrlDao(
        applicationDatabase: ApplicationDatabase
    ) = applicationDatabase.dogImageUrlDao()
}
