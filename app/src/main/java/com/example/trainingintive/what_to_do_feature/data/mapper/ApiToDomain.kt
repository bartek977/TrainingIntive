package com.example.trainingintive.what_to_do_feature.data.mapper

import com.example.trainingintive.what_to_do_feature.data.entity.ActivityModelFromApi

fun ActivityModelFromApi.toDomain() = com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel(activity)
