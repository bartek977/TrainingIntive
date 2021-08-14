package com.example.trainingintive.navigators

import android.content.Intent
import android.view.View
import com.example.trainingintive.R
import com.example.trainingintive.presentation.SplashActivity
import com.example.trainingintive.util.Event
import com.example.trainingintive.util.MainScreenEvent
import com.firebase.ui.auth.AuthUI
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainNavigator @Inject constructor() : Navigator() {

    override fun action(event: Event) {
        when (event) {
            is MainScreenEvent.Logout -> logoutAndDisplayStartScreen()
            is MainScreenEvent.Error -> showSnackBar(event.messageId)
        }
    }

    private fun logoutAndDisplayStartScreen() {
        // TODO You are using let but not using it's argument (it) so it is redundant, probably it is some typo and it should be used like in `showSnackBar`
        activity?.let {
            AuthUI.getInstance().signOut(it) // TODO It should be called from data layer (repository) and you should call it from ViewModel via UseCase
            activity?.startActivity(Intent(activity, SplashActivity::class.java))
            finishActivity()
        }
    }

    private fun showSnackBar(messageId: Int) {
        activity?.let { activity ->
            val view: View = activity.findViewById(R.id.myCoordinatorLayout)
            Snackbar.make(view, messageId, Snackbar.LENGTH_SHORT).show()
        }
    }
}
