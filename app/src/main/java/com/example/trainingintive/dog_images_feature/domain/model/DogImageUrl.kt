package com.example.trainingintive.dog_images_feature.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity
data class DogImageUrl(
    @PrimaryKey
    @Json(name = "message")
    val url: String,
    val status: String
)
