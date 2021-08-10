package com.example.trainingintive.dog_images_feature.data.entity

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.squareup.moshi.Json

data class DogImageUrlApi(
    @Json(name = "message")
    val url: String,
    val status: String
) {
    fun toDomain() = DogImageUrl(url)
}
