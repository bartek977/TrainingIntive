package com.example.trainingintive.repository

import com.example.trainingintive.model.DogImageUrl
import com.example.trainingintive.repository.network.DogImageApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DogImageRepository @Inject constructor(private val dogImageApiService: DogImageApiService) {

    fun getDogImageUrl(): Single<DogImageUrl> = dogImageApiService.getDogImage()
}
