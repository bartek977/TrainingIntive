package com.example.trainingintive.dog_images_feature.domain.usecase

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import javax.inject.Inject

class ChangeImagePositionsUseCase @Inject constructor(private val repository: DogImageRepository) {

    fun execute(urls: List<DogImageUrl>, from: Int, to: Int) =
        repository.changeImagePosition(urls, from, to)
}
