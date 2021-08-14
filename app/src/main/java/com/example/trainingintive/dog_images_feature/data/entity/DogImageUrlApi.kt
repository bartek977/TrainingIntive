package com.example.trainingintive.dog_images_feature.data.entity

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.squareup.moshi.Json

// TODO Api in name suggest that it is class with Retrofit or something network related.
// TODO it would be better to call such models "...ApiModel", so in this case "DogImageUrlApiModel"
data class DogImageUrlApi(
    @Json(name = "message")
    val url: String,
    val status: String
) {
    fun toDomain() = DogImageUrl(url)
}
