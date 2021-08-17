package com.example.trainingintive.what_to_do_feature.domain.usecase

import com.example.trainingintive.what_to_do_feature.domain.repository.ActivityRepository
import javax.inject.Inject

class GetActivitiesPagingSourceUseCase @Inject constructor(
    private val repository: ActivityRepository
) {

    fun execute() = repository.getPagingSource()
}
