package com.example.trainingintive.dog_images_feature.data.network

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DogImageApiService {
    @GET("api/breeds/image/random")
    fun getDogImage(): Single<DogImageUrl>
}
