package com.example.trainingintive

import android.app.Application
import com.example.trainingintive.data.UserSession
import com.example.trainingintive.di.AppComponent
import com.example.trainingintive.di.DaggerAppComponent
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth

class MyApplication : Application(), UserSession {

    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(this) }

    override fun logout() {
        AuthUI.getInstance().signOut(applicationContext)
    }

    override fun getUser() = FirebaseAuth.getInstance().currentUser
}
