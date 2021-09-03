package com.example.trainingintive.domain.usecase

import com.example.trainingintive.domain.repository.UserRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class LogoutUserUseCaseTest {

    val repository: UserRepository = mockk(relaxUnitFun = true)
    val tested = LogoutUserUseCase(repository)

    @Test
    fun logout() {
        tested.logout()

        verify { repository.logout() }
    }
}
