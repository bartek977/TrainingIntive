package com.example.trainingintive.what_to_do_feature.domain.usecase

import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import com.example.trainingintive.what_to_do_feature.domain.repository.ActivityRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetActivityUseCaseTest {

    val repository: ActivityRepository = mockk()
    val tested = GetActivityUseCase(repository)
    val activity = ActivityModel("nothing")

    @Test
    fun execute() {
        every { repository.getActivity() } returns Single.just(activity)

        tested.execute()

        verify { repository.getActivity() }
    }

    @Test
    fun `check returned value`() {
        every { repository.getActivity() } returns Single.just(activity)

        val result = tested.execute().test()

        result.assertValue(activity)
    }
}
