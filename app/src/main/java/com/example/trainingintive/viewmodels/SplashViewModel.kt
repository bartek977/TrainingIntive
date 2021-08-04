package com.example.trainingintive.viewmodels

import androidx.lifecycle.ViewModel
import com.example.trainingintive.navigators.SplashNavigator
import com.example.trainingintive.repository.Schedulers
import com.example.trainingintive.util.SplashScreenEvent
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val navigator: SplashNavigator,
    private val schedulers: Schedulers
) : ViewModel() {

    private val downloadData: Disposable

    init {
        downloadData = Single.timer(5, TimeUnit.SECONDS)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { navigator.sendEvent(SplashScreenEvent.DisplayUserScreen) },
                { navigator.sendEvent(SplashScreenEvent.Error) }
            )
    }

    override fun onCleared() {
        super.onCleared()
        downloadData.dispose()
    }

    fun onSuccesLogin() {
        navigator.sendEvent(SplashScreenEvent.DisplayUserScreen)
    }

    fun onFailedLogin() {
        navigator.sendEvent(SplashScreenEvent.Error)
    }
}
