package com.example.trainingintive.repository.network

import com.example.trainingintive.model.ActivityModel
import com.example.trainingintive.network.DogImageUrl
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    val dogImageApiService: DogImageApiService,
    val activityApiService: ActivityApiService
) {

    fun getDogImageUrl(): Single<DogImageUrl> = dogImageApiService.getDogImage()

    fun getActivity(): Single<ActivityModel> = activityApiService.getActivity()
}
