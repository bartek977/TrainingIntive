package com.example.trainingintive.what_to_do_feature.presentation

import com.example.trainingintive.RulesForTests
import com.example.trainingintive.what_to_do_feature.domain.usecase.GetActivitiesPagingSourceUseCase
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ActivitiesViewModelTest : RulesForTests() {

    val getActivitiesPagingSourceUseCase: GetActivitiesPagingSourceUseCase = mockk()
    val dispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when livedata have an observer, vm should invoke usecase`() {
        every { getActivitiesPagingSourceUseCase.execute() } returns mockk(relaxed = true)

        ActivitiesViewModel(getActivitiesPagingSourceUseCase).activities.observeForever { }

        verify { getActivitiesPagingSourceUseCase.execute() }
    }
}
