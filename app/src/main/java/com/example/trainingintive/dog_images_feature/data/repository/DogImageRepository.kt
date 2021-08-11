package com.example.trainingintive.dog_images_feature.data.repository

import com.example.trainingintive.dog_images_feature.data.entity.toDatabaseEntity
import com.example.trainingintive.dog_images_feature.data.local.DogImageDao
import com.example.trainingintive.dog_images_feature.data.network.DogImageApiService
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import javax.inject.Inject

class DogImageRepository @Inject constructor(
    private val dogImageApiService: DogImageApiService,
    private val dogImageDao: DogImageDao
) {

    fun getDogImageUrl() =
        dogImageApiService.getDogImage()
            .map { it.toDomain() }

    fun getAllImageUrls() =
        dogImageDao.getAllImageUrls()
            .map { it.map { it.toDomain() } }

    fun insert(imageUrl: DogImageUrl) = dogImageDao.insert(imageUrl.toDatabaseEntity())

    fun update(imageUrl: DogImageUrl) = dogImageDao.update(imageUrl.toDatabaseEntity())

    fun remove(imageUrl: DogImageUrl) = dogImageDao.remove(imageUrl.toDatabaseEntity())
}
