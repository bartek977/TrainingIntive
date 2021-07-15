package com.example.trainingintive.repository.network

import com.example.trainingintive.model.DogImageUrl
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface DogImageApiService {
    @GET("api/breeds/image/random")
    fun getDogImage(): Single<DogImageUrl>
}
