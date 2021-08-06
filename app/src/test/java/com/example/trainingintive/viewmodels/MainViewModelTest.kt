package com.example.trainingintive.viewmodels

import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.util.MainScreenEvent
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class MainViewModelTest {

    val navigator: MainNavigator = mockk(relaxUnitFun = true)
    val tested = MainViewModel(navigator)

    @Test
    fun `should send Logout event when logout called`() {
        tested.logout()

        verify { navigator.sendEvent(MainScreenEvent.Logout) }
    }
}
