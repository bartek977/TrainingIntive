package com.example.trainingintive.what_to_do_feature.di

import com.example.trainingintive.what_to_do_feature.data.repository.ActivityRepositoryImpl
import com.example.trainingintive.what_to_do_feature.domain.repository.ActivityRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Provides
    fun provideActivityRepository(
        activityRepositoryImpl: ActivityRepositoryImpl
    ): ActivityRepository = activityRepositoryImpl
}
