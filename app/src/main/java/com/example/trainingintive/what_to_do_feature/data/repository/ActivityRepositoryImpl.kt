package com.example.trainingintive.what_to_do_feature.data.repository

import com.example.trainingintive.what_to_do_feature.data.network.ActivityApiService
import com.example.trainingintive.what_to_do_feature.data.pagination.ActivityPagingSource
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import com.example.trainingintive.what_to_do_feature.domain.repository.ActivityRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ActivityRepositoryImpl @Inject constructor(
    private val activityApiService: ActivityApiService,
    private val pagingSource: ActivityPagingSource
) : ActivityRepository {

    override fun getActivity(): Single<ActivityModel> =
        activityApiService.getActivity()
            .map { it.toDomain() }

    override fun getPagingSource() = pagingSource
}
