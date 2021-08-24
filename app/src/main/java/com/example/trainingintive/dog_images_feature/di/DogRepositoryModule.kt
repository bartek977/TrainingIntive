package com.example.trainingintive.dog_images_feature.di

import com.example.trainingintive.dog_images_feature.data.repository.DogImageRepositoryImpl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import dagger.Module
import dagger.Provides

@Module
class DogRepositoryModule {

    @Provides
    fun provideDogImageRepository(
        dogImageRepositoryImpl: DogImageRepositoryImpl
    ): DogImageRepository = dogImageRepositoryImpl
}
