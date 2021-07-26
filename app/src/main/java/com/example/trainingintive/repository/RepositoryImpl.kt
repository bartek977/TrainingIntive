package com.example.trainingintive.repository

import com.example.trainingintive.model.ActivityModel
import com.example.trainingintive.network.DogImageUrl
import com.example.trainingintive.repository.network.NetworkRepository
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val networkRepository: NetworkRepository
) : Repository {

    override fun getDogImageUrl(): Single<DogImageUrl> = networkRepository.getDogImageUrl()
    override fun getActivity(): Single<ActivityModel> = networkRepository.getActivity()
}
