package com.example.trainingintive.what_to_do_feature.domain.usecase

import io.reactivex.rxjava3.core.Single

interface UseCase<R> {
    fun execute(): Single<R>
}
