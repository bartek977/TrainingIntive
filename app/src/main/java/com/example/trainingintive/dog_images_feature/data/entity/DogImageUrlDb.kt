package com.example.trainingintive.dog_images_feature.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.squareup.moshi.Json

@Entity
data class DogImageUrlDb(
    @PrimaryKey
    @Json(name = "message")
    val url: String,
    val position: Int
) {
    fun toDomain() = DogImageUrl(url, position)
}

fun DogImageUrl.toDatabaseEntity() = DogImageUrlDb(url, position)
