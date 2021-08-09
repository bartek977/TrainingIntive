package com.example.trainingintive.what_to_do_feature.data.entity

import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel

class ActivityModelApi(
    val activity: String,
    val type: String,
    val participants: Int
) {
    fun toDomain() = ActivityModel(activity)
}
