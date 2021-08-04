package com.example.trainingintive.viewmodels

import androidx.lifecycle.ViewModel
import com.example.trainingintive.navigators.SplashNavigator
import com.example.trainingintive.util.SplashScreenEvent
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(private val navigator: SplashNavigator) : ViewModel() {

    private val downloadData: Disposable

    init {
        downloadData = Single.timer(5, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
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
