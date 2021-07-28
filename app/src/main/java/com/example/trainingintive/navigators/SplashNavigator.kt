package com.example.trainingintive.navigators

import android.content.Intent
import com.example.trainingintive.MainActivity
import com.example.trainingintive.firebase.FirebaseUserLiveData
import com.example.trainingintive.util.Event
import com.example.trainingintive.util.SplashScreenEvent
import com.firebase.ui.auth.AuthUI
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SplashNavigator @Inject constructor(private val firebaseUserLiveData: FirebaseUserLiveData) : Navigator() {

    override fun action(event: Event) {
        when (event) {
            is SplashScreenEvent.DisplayUserScreen -> observeUserStateAndDisplayLoginOrMainScreen()
            is SplashScreenEvent.Error -> finishActivity()
        }
    }

    private fun observeUserStateAndDisplayLoginOrMainScreen() {
        activity?.let {
            firebaseUserLiveData.observe(
                it,
                { user ->
                    if (user == null) {
                        launchSignInFlow()
                    } else {
                        navigateToMainActivity()
                    }
                }
            )
        }
    }

    private fun navigateToMainActivity() {
        activity?.startActivity(Intent(activity, MainActivity::class.java))
        finishActivity()
    }

    private fun finishActivity() {
        activity?.finish()
    }

    private fun launchSignInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        activity?.startActivity(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
        )
    }
}
