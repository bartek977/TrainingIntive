package com.example.trainingintive.repository

import com.example.trainingintive.network.DogImageUrl
import io.reactivex.rxjava3.core.Single

interface Repository {

    fun getDogImageUrl(): Single<DogImageUrl>
}
