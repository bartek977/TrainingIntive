package com.example.trainingintive.what_to_do_feature.domain.usecase

import com.example.trainingintive.what_to_do_feature.data.pagination.ActivityPagingSource
import com.example.trainingintive.what_to_do_feature.domain.repository.ActivityRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.junit.Test

class GetActivitiesPagingSourceUseCaseTest {

    val repository: ActivityRepository = mockk()
    val tested = GetActivitiesPagingSourceUseCase(repository)
    val pagingSource: ActivityPagingSource = mockk()

    @Test
    fun execute() {
        every { repository.getPagingSource() } returns pagingSource

        tested.execute()

        verify { repository.getPagingSource() }
    }

    @Test
    fun `check returned value`() {
        every { repository.getPagingSource() } returns pagingSource

        val returnedPagingSource = tested.execute()

        returnedPagingSource shouldBe pagingSource
    }
}
