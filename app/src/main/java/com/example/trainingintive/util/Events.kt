package com.example.trainingintive.util

interface Event {
    object Nothing : Event
}

sealed class SplashScreenEvent : Event {
    object DisplayUserScreen : SplashScreenEvent()
    object Error : SplashScreenEvent()
}

sealed class MainScreenEvent : Event {
    object Logout : MainScreenEvent()
}
