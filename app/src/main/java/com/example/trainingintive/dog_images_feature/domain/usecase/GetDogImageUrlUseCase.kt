package com.example.trainingintive.dog_images_feature.domain.usecase

import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import javax.inject.Inject

class GetDogImageUrlUseCase @Inject constructor(private val repository: DogImageRepository) {

    fun execute() = repository.getDogImageUrl()
}
