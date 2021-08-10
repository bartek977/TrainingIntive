package com.example.trainingintive.navigators

import android.content.Intent
import com.example.trainingintive.presentation.SplashActivity
import com.example.trainingintive.util.Event
import com.example.trainingintive.util.MainScreenEvent
import com.firebase.ui.auth.AuthUI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainNavigator @Inject constructor() : Navigator() {

    override fun action(event: Event) {
        when (event) {
            is MainScreenEvent.Logout -> logoutAndDisplayStartScreen()
        }
    }

    private fun logoutAndDisplayStartScreen() {
        activity?.let {
            AuthUI.getInstance().signOut(it)
            activity?.startActivity(Intent(activity, SplashActivity::class.java))
            finishActivity()
        }
    }
}
