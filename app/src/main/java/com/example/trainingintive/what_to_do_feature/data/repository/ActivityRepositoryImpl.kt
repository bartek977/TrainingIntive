package com.example.trainingintive.what_to_do_feature.data.repository

import com.example.trainingintive.what_to_do_feature.data.mapper.toDomain
import com.example.trainingintive.what_to_do_feature.data.network.ActivityApiService
import com.example.trainingintive.what_to_do_feature.domain.repository.ActivityRepository
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(private val activityApiService: ActivityApiService) : ActivityRepository {

    override fun getActivity() = activityApiService.getActivity().map { it.toDomain() }
}
