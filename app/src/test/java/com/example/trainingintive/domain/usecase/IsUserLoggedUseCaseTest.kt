package com.example.trainingintive.domain.usecase

import com.example.trainingintive.domain.repository.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.Test

class IsUserLoggedUseCaseTest {

    val repository: UserRepository = mockk()
    val tested = IsUserLoggedUseCase(repository)

    @Test
    fun `should return true when repository return true`() {
        every { repository.isUserLogged() } returns true

        val result = tested.isLogged()

        result shouldBe true
    }

    @Test
    fun `should return false when repository return false`() {
        every { repository.isUserLogged() } returns false

        val result = tested.isLogged()

        result shouldBe false
    }

    @Test
    fun `should invoke repository method`() {
        every { repository.isUserLogged() } returns true

        tested.isLogged()

        verify { repository.isUserLogged() }
    }
}
