package com.example.trainingintive.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

open class SchedulersProvider @Inject constructor() {

    open fun io() = Schedulers.io()
    open fun ui() = AndroidSchedulers.mainThread()
}
