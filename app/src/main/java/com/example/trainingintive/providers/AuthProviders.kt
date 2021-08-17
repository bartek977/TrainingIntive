package com.example.trainingintive.providers

import com.firebase.ui.auth.AuthUI

object AuthProviders {
    val providers = arrayListOf(
        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )
}
