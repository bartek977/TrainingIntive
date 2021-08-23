package com.example.trainingintive.presentation

import androidx.lifecycle.ViewModel
import com.example.trainingintive.domain.usecase.IsUserLoggedUseCase
import com.example.trainingintive.navigators.SplashNavigator
import com.example.trainingintive.rx.SchedulersProvider
import com.example.trainingintive.util.SplashScreenEvent
import com.example.trainingintive.util.plusAssign
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit
import javax.inject.Inject

private const val DELAY_IN_SECONDS = 5L

class SplashViewModel @Inject constructor(
    private val navigator: SplashNavigator,
    private val schedulers: SchedulersProvider,
    private val isUserLoggedUseCase: IsUserLoggedUseCase
) : ViewModel() {

    private val disposables = CompositeDisposable()

    fun start() {
        disposables += Single.timer(DELAY_IN_SECONDS, TimeUnit.SECONDS)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
            .subscribe(
                { checkIfUserIsLoggedAndSendEventToNavigator() },
                { navigator.sendEvent(SplashScreenEvent.Error) }
            )
    }

    private fun checkIfUserIsLoggedAndSendEventToNavigator() {
        if (isUserLoggedUseCase.isLogged()) {
            navigator.sendEvent(SplashScreenEvent.DisplayUserScreen)
        } else {
            navigator.sendEvent(SplashScreenEvent.DisplayLogInForm)
        }
    }

    fun onSuccesLogin() {
        navigator.sendEvent(SplashScreenEvent.DisplayUserScreen)
    }

    fun onFailedLogin() {
        navigator.sendEvent(SplashScreenEvent.Error)
    }

    override fun onCleared() {
        super.onCleared()
        disposables.dispose()
    }
}
