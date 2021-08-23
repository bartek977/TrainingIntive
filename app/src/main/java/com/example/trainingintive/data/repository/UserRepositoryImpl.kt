package com.example.trainingintive.data.repository

import com.example.trainingintive.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor() : UserRepository {
    override fun isUserLogged() = FirebaseAuth.getInstance().currentUser != null
}
