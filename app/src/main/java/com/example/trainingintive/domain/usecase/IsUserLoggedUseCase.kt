package com.example.trainingintive.domain.usecase

import com.example.trainingintive.domain.repository.UserRepository
import javax.inject.Inject

class IsUserLoggedUseCase @Inject constructor(
    private val repository: UserRepository
) {
    fun isLogged() = repository.isUserLogged()
}
