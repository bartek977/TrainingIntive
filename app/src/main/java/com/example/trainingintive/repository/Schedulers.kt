package com.example.trainingintive.repository

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

open class Schedulers @Inject constructor() {

    fun io() = Schedulers.io()
    fun ui() = AndroidSchedulers.mainThread()
}
