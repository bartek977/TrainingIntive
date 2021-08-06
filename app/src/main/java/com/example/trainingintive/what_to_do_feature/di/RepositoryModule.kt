package com.example.trainingintive.what_to_do_feature.di

import com.example.trainingintive.what_to_do_feature.data.repository.ActivityRepositoryImpl
import com.example.trainingintive.what_to_do_feature.domain.repository.ActivityRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideActivityRepository(
        activityRepositoryImpl: ActivityRepositoryImpl
    ): ActivityRepository = activityRepositoryImpl
}
