package com.example.trainingintive.presentation

import androidx.lifecycle.ViewModel
import com.example.trainingintive.navigators.SplashNavigator
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.util.SplashScreenEvent
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    private val navigator: SplashNavigator,
    private val schedulers: SchedulersProvider
) : ViewModel() {

    // TOOD Use CompositeDisposables for consistency
    private val downloadData: Disposable

    init {
        // TODO Delay time (5 seconds) should be extracted to some constant with proper name
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
