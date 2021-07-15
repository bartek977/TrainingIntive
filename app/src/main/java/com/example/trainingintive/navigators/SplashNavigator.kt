package com.example.trainingintive.navigators

import android.content.Intent
import com.example.trainingintive.MainActivity
import com.example.trainingintive.util.Event
import com.example.trainingintive.util.SplashScreenEvent

class SplashNavigator() : Navigator() {

    override fun action(event: Event) {
        when (event) {
            is SplashScreenEvent.DisplayUserScreen -> navigateToMainActivity()
            is SplashScreenEvent.Error -> finishActivity()
        }
    }

    private fun navigateToMainActivity() {
        activity?.startActivity(Intent(activity, MainActivity::class.java))
        finishActivity()
    }

    private fun finishActivity() {
        activity?.finish()
    }
}
