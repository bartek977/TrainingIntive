package com.example.trainingintive.util

interface Event {
    object Nothing : Event
}

sealed class SplashScreenEvent : Event {
    object DisplayUserScreen : SplashScreenEvent()
    object Error : SplashScreenEvent()
    object DisplayLogInForm : SplashScreenEvent()
}

sealed class MainScreenEvent : Event {
    class Error(val messageId: Int) : Event

    object Logout : MainScreenEvent()
}
