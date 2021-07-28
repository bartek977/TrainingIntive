package com.example.trainingintive.repository

import com.example.trainingintive.model.ActivityModel
import com.example.trainingintive.repository.network.ActivityApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ActivityRepository @Inject constructor(private val activityApiService: ActivityApiService) {

    fun getActivity(): Single<ActivityModel> = activityApiService.getActivity()
}
