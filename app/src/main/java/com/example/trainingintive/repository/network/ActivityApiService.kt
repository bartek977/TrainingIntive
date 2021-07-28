package com.example.trainingintive.repository.network

import com.example.trainingintive.model.ActivityModel
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ActivityApiService {
    @GET("api/activity")
    fun getActivity(): Single<ActivityModel>
}
