package com.example.trainingintive.viewmodels

import com.example.trainingintive.TestSchedulersProvider
import com.example.trainingintive.navigators.SplashNavigator
import com.example.trainingintive.presentation.SplashViewModel
import com.example.trainingintive.util.SplashScreenEvent
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Test
import java.util.concurrent.TimeUnit

class SplashViewModelTest {

    val navigator: SplashNavigator = mockk(relaxUnitFun = true)
    val schedulersForTest = TestSchedulersProvider()
    val tested = SplashViewModel(navigator, schedulersForTest)

    @After
    fun tearDown() = RxJavaPlugins.reset()

    @Test
    fun `after init, viewmodel should send DisplayUserScreen event`() {
        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }

        SplashViewModel(navigator, schedulersForTest)
        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)

        verify { navigator.sendEvent(SplashScreenEvent.DisplayUserScreen) }
    }

    @Test
    fun `after success login, viewmodel should send DisplayUserScreen event`() {
        tested.onSuccesLogin()

        verify { navigator.sendEvent(SplashScreenEvent.DisplayUserScreen) }
    }

    @Test
    fun `after failed login, viewmodel should send Error event`() {
        tested.onFailedLogin()

        verify { navigator.sendEvent(SplashScreenEvent.Error) }
    }
}
