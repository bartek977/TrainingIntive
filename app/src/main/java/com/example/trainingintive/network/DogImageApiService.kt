package com.example.trainingintive.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL =
    "https://dog.ceo"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface DogImageApiService {
    @GET("api/breeds/image/random")
    fun getDogImage(): Response<DogImage>
}

object DogImageApi {
    val retrofitService : DogImageApiService by lazy {
        retrofit.create(DogImageApiService::class.java) }
}

data class DogImage(
    val message: String,
    val status: String
)