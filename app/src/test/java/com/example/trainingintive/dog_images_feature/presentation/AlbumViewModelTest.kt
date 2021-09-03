package com.example.trainingintive.dog_images_feature.presentation

import com.example.trainingintive.RulesForTests
import com.example.trainingintive.TestSchedulersProvider
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.usecase.ChangeImagePositionsUseCase
import com.example.trainingintive.dog_images_feature.domain.usecase.GetAllUrlsUseCase
import com.example.trainingintive.dog_images_feature.domain.usecase.RemoveDogImageUseCase
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.util.MainScreenEvent
import com.example.trainingintive.util.toErrorTextId
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class AlbumViewModelTest : RulesForTests() {

    val getAllUrlsUseCase: GetAllUrlsUseCase = mockk()
    val removeDogImageUseCase: RemoveDogImageUseCase = mockk()
    val changeImagePositionsUseCase: ChangeImagePositionsUseCase = mockk()
    val schedulers = TestSchedulersProvider()
    val navigator: MainNavigator = mockk(relaxUnitFun = true)
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
    fun `when viewmodel finish init, livedata should contain dog image urls sorted by position`() {
        val url1: DogImageUrl = mockk {
            every { position } returns 1
        }
        val url2: DogImageUrl = mockk {
            every { position } returns 0
        }
        val list = listOf(url1, url2)
        val sortedList = listOf(url2, url1)
        every { getAllUrlsUseCase.execute() } returns Flowable.just(list)

        val tested = AlbumViewModel(
            getAllUrlsUseCase,
            removeDogImageUseCase,
            changeImagePositionsUseCase,
            schedulers,
            navigator
        )

        tested.imageUrls.observeForever { result ->
            result shouldBeEqualTo sortedList
        }
    }

    @Test
    fun `if repository returns error vm should send error event`() {
        every { getAllUrlsUseCase.execute() } returns Flowable.error(exception)

        AlbumViewModel(
            getAllUrlsUseCase,
            removeDogImageUseCase,
            changeImagePositionsUseCase,
            schedulers,
            navigator
        )

        verify { navigator.sendEvent(ofType(MainScreenEvent.Error::class)) }
    }

    @Test
    fun `if repository returns error vm should call toErrorTextId method`() {
        every { getAllUrlsUseCase.execute() } returns Flowable.error(exception)

        AlbumViewModel(
            getAllUrlsUseCase,
            removeDogImageUseCase,
            changeImagePositionsUseCase,
            schedulers,
            navigator
        )

        verify { exception.toErrorTextId() }
    }

    @Test
    fun `test removing`() {
        val url1: DogImageUrl = mockk {
            every { position } returns 0
        }
        val url2: DogImageUrl = mockk {
            every { position } returns 1
        }
        val sortedList = listOf(url1, url2)
        every { getAllUrlsUseCase.execute() } returns Flowable.just(sortedList)
        every { removeDogImageUseCase.execute(any()) } returns Completable.complete()

        AlbumViewModel(
            getAllUrlsUseCase,
            removeDogImageUseCase,
            changeImagePositionsUseCase,
            schedulers,
            navigator
        ).removeImage(0)

        verify { removeDogImageUseCase.execute(url1) }
    }

    @Test
    fun `test changing positions`() {
        val url1: DogImageUrl = mockk {
            every { position } returns 0
        }
        val url2: DogImageUrl = mockk {
            every { position } returns 1
        }
        val sortedList = listOf(url1, url2)
        every { getAllUrlsUseCase.execute() } returns Flowable.just(sortedList)
        every { changeImagePositionsUseCase.execute(any(), any(), any()) } returns Completable.complete()

        AlbumViewModel(
            getAllUrlsUseCase,
            removeDogImageUseCase,
            changeImagePositionsUseCase,
            schedulers,
            navigator
        ).changeImagePositionAndUpdateDb(1, 0)

        verify { changeImagePositionsUseCase.execute(sortedList, 1, 0) }
    }
}
