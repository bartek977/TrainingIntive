package com.example.trainingintive.what_to_do_feature.data.repository

import com.example.trainingintive.what_to_do_feature.data.network.ActivityApiService
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ActivityRepository @Inject constructor(private val activityApiService: ActivityApiService) {

    fun getActivity(): Single<ActivityModel> =
        activityApiService.getActivity()
            .map { it.toDomain() }
}
