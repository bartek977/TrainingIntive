package com.example.trainingintive.providers

import com.firebase.ui.auth.AuthUI
import javax.inject.Inject

class LoginIntentBuilder @Inject constructor() {
    fun build() =
        AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(AuthProviders.providers)
            .build()
}
