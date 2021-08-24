package com.example.trainingintive.presentation

import com.example.trainingintive.TestSchedulersProvider
import com.example.trainingintive.domain.usecase.IsUserLoggedUseCase
import com.example.trainingintive.navigators.SplashNavigator
import com.example.trainingintive.util.SplashScreenEvent
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class SplashViewModelTest {

    val navigator: SplashNavigator = mockk(relaxUnitFun = true)
    val schedulersForTest = TestSchedulersProvider()
    val isUserLoggedUseCase: IsUserLoggedUseCase = mockk()
    val tested = SplashViewModel(navigator, schedulersForTest, isUserLoggedUseCase)
    val testScheduler = TestScheduler()

    @Before
    fun setup() {
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
    }

    @After
    fun tearDown() = RxJavaPlugins.reset()

    @Test
    fun `after start, if user is logged in vm should send DisplayUserScreen event`() {
        every { isUserLoggedUseCase.isLogged() } returns true

        tested.start()
        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)

        verify { navigator.sendEvent(SplashScreenEvent.DisplayUserScreen) }
    }

    @Test
    fun `after start, if user is logged out vm should send DisplayLoginForm event`() {
        val testScheduler = TestScheduler()
        RxJavaPlugins.setComputationSchedulerHandler { testScheduler }
        every { isUserLoggedUseCase.isLogged() } returns false

        tested.start()
        testScheduler.advanceTimeBy(5, TimeUnit.SECONDS)

        verify { navigator.sendEvent(SplashScreenEvent.DisplayLogInForm) }
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
