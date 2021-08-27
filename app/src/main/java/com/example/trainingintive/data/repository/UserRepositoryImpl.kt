package com.example.trainingintive.data.repository

import com.example.trainingintive.data.UserSession
import com.example.trainingintive.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val userSession: UserSession) : UserRepository {

    override fun isUserLogged() = userSession.getUser() != null

    override fun logout() = userSession.logout()
}
