package com.example.trainingintive.dog_images_feature.presentation

import com.example.trainingintive.RulesForTests
import com.example.trainingintive.TestSchedulersProvider
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.usecase.GetDogImageUrlUseCase
import com.example.trainingintive.dog_images_feature.domain.usecase.InsertDogImageUrlUseCase
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.util.MainScreenEvent
import com.example.trainingintive.util.toErrorTextId
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class DogImageViewModelTest : RulesForTests() {

    val getDogImageUrlUseCase: GetDogImageUrlUseCase = mockk()
    val insertDogImageUrlUseCase: InsertDogImageUrlUseCase = mockk()
    val schedulersForTests = TestSchedulersProvider()
    val mainNavigator: MainNavigator = mockk(relaxUnitFun = true)
    val exception: Exception = mockk()

    @Before
    fun setup() {
        mockkStatic(exception::toErrorTextId)
    }

    @After
    fun tearDown() {
        unmockkStatic(exception::toErrorTextId)
    }

    @Test
    fun `when viewmodel finish init, livedata should contain dog imiage url`() {
        val sampleDog: DogImageUrl = mockk(relaxed = true)
        every { getDogImageUrlUseCase.execute() } returns Single.just(sampleDog)
        every { insertDogImageUrlUseCase.execute(any()) } returns Completable.complete()
        val tested = DogImageViewModel(
            getDogImageUrlUseCase,
            insertDogImageUrlUseCase,
            schedulersForTests,
            mainNavigator
        )

        tested.dog.observeForever { result ->
            result shouldBeEqualTo sampleDog.url
        }
    }

    @Test
    fun `after successfully download, dogimage should be inserted into local database`() {
        val sampleDog: DogImageUrl = mockk(relaxed = true)
        every { getDogImageUrlUseCase.execute() } returns Single.just(sampleDog)
        every { insertDogImageUrlUseCase.execute(any()) } returns Completable.complete()

        DogImageViewModel(
            getDogImageUrlUseCase,
            insertDogImageUrlUseCase,
            schedulersForTests,
            mainNavigator
        )

        verify { insertDogImageUrlUseCase.execute(sampleDog) }
    }

    @Test
    fun `if repository returns error vm should send error event`() {
        every { getDogImageUrlUseCase.execute() } returns Single.error(exception)

        DogImageViewModel(
            getDogImageUrlUseCase,
            insertDogImageUrlUseCase,
            schedulersForTests,
            mainNavigator
        )

        verify { mainNavigator.sendEvent(ofType(MainScreenEvent.Error::class)) }
    }

    @Test
    fun `if repository returns error vm should call toErrorTextId method`() {
        every { getDogImageUrlUseCase.execute() } returns Single.error(exception)

        DogImageViewModel(
            getDogImageUrlUseCase,
            insertDogImageUrlUseCase,
            schedulersForTests,
            mainNavigator
        )

        verify { exception.toErrorTextId() }
    }
}
