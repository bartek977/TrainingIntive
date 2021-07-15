package com.example.trainingintive.model

import com.squareup.moshi.Json

data class DogImageUrl(
    @Json(name = "message")
    val url: String,
    val status: String
)
