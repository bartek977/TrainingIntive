package com.example.trainingintive.presentation

import androidx.lifecycle.ViewModel
import com.example.trainingintive.domain.usecase.LogoutUserUseCase
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.util.MainScreenEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val navigator: MainNavigator,
    private val logoutUserUseCase: LogoutUserUseCase
) : ViewModel() {

    fun logout() {
        logoutUserUseCase.logout()
        navigator.sendEvent(MainScreenEvent.Logout)
    }
}
