package com.example.trainingintive.dog_images_feature.data.repository

import com.example.trainingintive.dog_images_feature.data.entity.toDatabaseEntity
import com.example.trainingintive.dog_images_feature.data.local.DogImageDao
import com.example.trainingintive.dog_images_feature.data.network.DogImageApiService
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import io.reactivex.rxjava3.core.Single
import java.util.Collections
import javax.inject.Inject

class DogImageRepositoryImpl @Inject constructor(
    private val dogImageApiService: DogImageApiService,
    private val dogImageDao: DogImageDao
) : DogImageRepository {

    override fun getDogImageUrl() =
        getLastPosition().flatMap {
            getDogImage(it)
        }

    override fun getAllImageUrls() =
        dogImageDao.getAllImageUrls()
            .map { it.map { it.toDomain() } }

    override fun insert(imageUrl: DogImageUrl) = dogImageDao.insert(imageUrl.toDatabaseEntity())

    override fun update(imageUrl: DogImageUrl) = dogImageDao.update(imageUrl.toDatabaseEntity())

    override fun remove(imageUrl: DogImageUrl) = dogImageDao.remove(imageUrl.toDatabaseEntity())

    override fun update(imageUrls: List<DogImageUrl>) =
        dogImageDao.update(imageUrls.map { it.toDatabaseEntity() })

    override fun changeImagePosition(urls: List<DogImageUrl>, from: Int, to: Int) =
        Single.fromCallable {
            changePositions(urls, from, to)
        }.flatMapCompletable {
            update(it)
        }

    private fun getLastPosition() = dogImageDao.count()

    private fun getDogImage(position: Int) =
        dogImageApiService.getDogImage().map {
            it.toDomain(position)
        }

    private fun changePositions(urls: List<DogImageUrl>, from: Int, to: Int): List<DogImageUrl> {
        val list = urls.toMutableList()
        Collections.swap(list, from, to)
        list.forEachIndexed { index, url -> url.position = index }
        return list
    }
}
