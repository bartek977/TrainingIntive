package com.example.trainingintive.what_to_do_feature.domain.repository

import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import io.reactivex.rxjava3.core.Single

interface ActivityRepository {

    fun getActivity(): Single<ActivityModel>
}
