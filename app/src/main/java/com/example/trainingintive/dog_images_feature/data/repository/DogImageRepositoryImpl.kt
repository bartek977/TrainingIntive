package com.example.trainingintive.dog_images_feature.data.repository

import com.example.trainingintive.dog_images_feature.data.entity.toDatabaseEntity
import com.example.trainingintive.dog_images_feature.data.local.DogImageDao
import com.example.trainingintive.dog_images_feature.data.network.DogImageApiService
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import javax.inject.Inject

class DogImageRepositoryImpl @Inject constructor(
    private val dogImageApiService: DogImageApiService,
    private val dogImageDao: DogImageDao
) : DogImageRepository {

    override fun getDogImageUrl() =
        dogImageApiService.getDogImage()
            .map { it.toDomain() }

    override fun getAllImageUrls() =
        dogImageDao.getAllImageUrls()
            .map { it.map { it.toDomain() } }

    override fun insert(imageUrl: DogImageUrl) = dogImageDao.insert(imageUrl.toDatabaseEntity())

    override fun update(imageUrl: DogImageUrl) = dogImageDao.update(imageUrl.toDatabaseEntity())

    override fun remove(imageUrl: DogImageUrl) = dogImageDao.remove(imageUrl.toDatabaseEntity())
}
