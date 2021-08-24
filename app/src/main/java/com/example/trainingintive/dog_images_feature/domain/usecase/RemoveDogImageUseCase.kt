package com.example.trainingintive.dog_images_feature.domain.usecase

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import javax.inject.Inject

class RemoveDogImageUseCase @Inject constructor(private val repository: DogImageRepository) {

    fun execute(imageUrl: DogImageUrl) = repository.remove(imageUrl)
}
