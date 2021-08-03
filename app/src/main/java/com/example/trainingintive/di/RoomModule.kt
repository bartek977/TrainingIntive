package com.example.trainingintive.di

import android.content.Context
import androidx.room.Room
import com.example.trainingintive.repository.local.ApplicationDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val DATABASE_NAME = "application_database"

@Module
class RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            ApplicationDatabase::class.java,
            DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideDogImageUrlDao(
        applicationDatabase: ApplicationDatabase
    ) = applicationDatabase.dogImageUrlDao()
}
