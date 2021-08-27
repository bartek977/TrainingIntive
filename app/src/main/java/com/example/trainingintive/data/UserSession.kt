package com.example.trainingintive.data

import com.google.firebase.auth.FirebaseUser

interface UserSession {

    fun logout()
    fun getUser(): FirebaseUser?
}
