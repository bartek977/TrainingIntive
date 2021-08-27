package com.example.trainingintive.data.repository

import android.content.Context
import com.example.trainingintive.domain.repository.UserRepository
import com.firebase.ui.auth.AuthUI
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val context: Context) : UserRepository {
    override fun isUserLogged() = FirebaseAuth.getInstance().currentUser != null

    override fun logout() {
        AuthUI.getInstance().signOut(context)
    }
}
