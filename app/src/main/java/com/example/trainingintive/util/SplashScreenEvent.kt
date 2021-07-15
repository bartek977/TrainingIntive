package com.example.trainingintive.util

interface Event

sealed class SplashScreenEvent : Event {
    object DisplayUserScreen : SplashScreenEvent()
    object Error : SplashScreenEvent()
}
