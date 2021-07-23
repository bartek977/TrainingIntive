package com.example.trainingintive.navigators

import android.content.Intent
import com.example.trainingintive.MainActivity
import com.example.trainingintive.util.Event
import com.example.trainingintive.util.SplashScreenEvent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject
import javax.inject.Singleton

const val SIGN_IN_RESULT_CODE = 1001

@Singleton
class SplashNavigator @Inject constructor() : Navigator() {

    override fun action(event: Event) {
        when (event) {
            is SplashScreenEvent.DisplayUserScreen -> checkIfUserIsLoggedAndDisplayMainOrLoginScreen()
            is SplashScreenEvent.Error -> finishActivity()
        }
    }

    private fun checkIfUserIsLoggedAndDisplayMainOrLoginScreen() {

        if (isUserLogged()) {
            navigateToMainActivity()
        } else {
            launchSignInFlow()
        }
    }

    private fun navigateToMainActivity() {
        activity?.startActivity(Intent(activity, MainActivity::class.java))
        finishActivity()
    }

    private fun launchSignInFlow() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.EmailBuilder().build(),
            AuthUI.IdpConfig.GoogleBuilder().build()
        )
        activity?.startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            SIGN_IN_RESULT_CODE
        )
    }

    private fun isUserLogged() = FirebaseAuth.getInstance().currentUser != null
}
