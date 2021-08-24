package com.example.trainingintive.dog_images_feature.domain.repository

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface DogImageRepository {

    fun getDogImageUrl(): Single<DogImageUrl>

    fun getAllImageUrls(): Flowable<List<DogImageUrl>>

    fun insert(imageUrl: DogImageUrl): Completable

    fun update(imageUrl: DogImageUrl): Completable

    fun remove(imageUrl: DogImageUrl): Completable

    fun update(imageUrls: List<DogImageUrl>): Completable

    fun changeImagePosition(urls: List<DogImageUrl>, from: Int, to: Int): Completable
}
