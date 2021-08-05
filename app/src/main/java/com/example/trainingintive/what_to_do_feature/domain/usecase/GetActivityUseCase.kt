package com.example.trainingintive.what_to_do_feature.domain.usecase

import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import com.example.trainingintive.what_to_do_feature.domain.repository.ActivityRepository
import javax.inject.Inject

class GetActivityUseCase @Inject constructor(private val repository: ActivityRepository) : UseCase<ActivityModel> {

    override fun execute() = repository.getActivity()
}
