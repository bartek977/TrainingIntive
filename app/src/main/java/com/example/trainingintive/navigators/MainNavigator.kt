package com.example.trainingintive.navigators

import android.content.Intent
import android.view.View
import com.example.trainingintive.R
import com.example.trainingintive.presentation.SplashActivity
import com.example.trainingintive.util.Event
import com.example.trainingintive.util.MainScreenEvent
import com.google.android.material.snackbar.Snackbar
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainNavigator @Inject constructor() : Navigator() {

    override fun action(event: Event) {
        when (event) {
            is MainScreenEvent.Logout -> displayStartScreen()
            is MainScreenEvent.Error -> showSnackBar(event.messageId)
        }
    }

    private fun displayStartScreen() {
        activity?.let { activity ->
            activity.startActivity(Intent(activity, SplashActivity::class.java))
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
