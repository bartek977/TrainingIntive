package com.example.trainingintive.repository

import com.example.trainingintive.model.ActivityModel
import com.example.trainingintive.model.DogImageUrl
import com.example.trainingintive.repository.network.NetworkRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository
) : Repository {

    override fun getDogImageUrl(): Single<DogImageUrl> = networkRepository.getDogImageUrl()
    override fun getActivity(): Single<ActivityModel> = networkRepository.getActivity()
}
