package com.example.trainingintive.repository

import com.example.trainingintive.model.DogImageUrl
import com.example.trainingintive.repository.local.DogImageDao
import com.example.trainingintive.repository.network.DogImageApiService
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class DogImageRepository @Inject constructor(
    private val dogImageApiService: DogImageApiService,
    private val dogImageDao: DogImageDao
) {

    fun getDogImageUrl(): Single<DogImageUrl> = dogImageApiService.getDogImage()

    fun getAllImageUrlsFromLocalDatabase(): Flowable<List<DogImageUrl>> = dogImageDao.getAllImageUrls()

    fun insertIntoLocalDatabase(dogImageUrl: DogImageUrl): Completable = dogImageDao.insert(dogImageUrl)
}
