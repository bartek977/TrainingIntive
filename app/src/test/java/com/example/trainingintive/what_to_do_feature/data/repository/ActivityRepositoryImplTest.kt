package com.example.trainingintive.what_to_do_feature.data.repository

import com.example.trainingintive.what_to_do_feature.data.entity.ActivityModelApi
import com.example.trainingintive.what_to_do_feature.data.network.ActivityApiService
import com.example.trainingintive.what_to_do_feature.data.pagination.ActivityPagingSource
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.amshove.kluent.shouldBe
import org.junit.Test

class ActivityRepositoryImplTest {

    val activityApiService: ActivityApiService = mockk()
    val pagingSource: ActivityPagingSource = mockk()
    val tested = ActivityRepositoryImpl(activityApiService, pagingSource)

    @Test
    fun `check returned value from getActivity()`() {
        val expected: ActivityModel = mockk()
        val activityModelApi: ActivityModelApi = mockk {
            every { toDomain() } returns expected
        }
        every { activityApiService.getActivity() } returns Single.just(activityModelApi)

        val result = tested.getActivity().test()

        result.assertValue(expected)
    }

    @Test
    fun `getActivity() should invoke ApiService`() {
        every { activityApiService.getActivity() } returns Single.just(mockk())

        tested.getActivity().test()

        verify { activityApiService.getActivity() }
    }

    @Test
    fun `check returned value from getPagingSource()`() {
        val result = tested.getPagingSource()

        result shouldBe pagingSource
    }
}
