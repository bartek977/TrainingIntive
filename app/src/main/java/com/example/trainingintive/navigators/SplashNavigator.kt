package com.example.trainingintive.navigators

import android.content.Intent
import com.example.trainingintive.presentation.MainActivity
import com.example.trainingintive.providers.LoginIntentBuilder
import com.example.trainingintive.util.Event
import com.example.trainingintive.util.SplashScreenEvent
import javax.inject.Inject
import javax.inject.Singleton

const val SIGN_IN_RESULT_CODE = 1001

@Singleton
class SplashNavigator @Inject constructor(
    private val loginIntentBuilder: LoginIntentBuilder
) : Navigator() {

    override fun action(event: Event) {
        when (event) {
            is SplashScreenEvent.DisplayUserScreen -> navigateToMainActivity()
            is SplashScreenEvent.DisplayLogInForm -> launchSignInFlow()
            is SplashScreenEvent.Error -> finishActivity()
        }
    }

    private fun navigateToMainActivity() {
        activity?.startActivity(Intent(activity, MainActivity::class.java))
        finishActivity()
    }

    private fun launchSignInFlow() {
        activity?.startActivityForResult(
            loginIntentBuilder.build(),
            SIGN_IN_RESULT_CODE
        )
    }
}
