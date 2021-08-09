package com.example.trainingintive.what_to_do_feature.data.network

import com.example.trainingintive.what_to_do_feature.data.entity.ActivityModelApi
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ActivityApiService {
    @GET("api/activity")
    fun getActivity(): Single<ActivityModelApi>
}
