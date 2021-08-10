package com.example.trainingintive.presentation

import androidx.lifecycle.ViewModel
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.util.MainScreenEvent
import javax.inject.Inject

class MainViewModel @Inject constructor(private val navigator: MainNavigator) : ViewModel() {

    fun logout() {
        navigator.sendEvent(MainScreenEvent.Logout)
    }
}
