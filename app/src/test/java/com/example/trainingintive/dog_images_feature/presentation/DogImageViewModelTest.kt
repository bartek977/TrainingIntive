package com.example.trainingintive.dog_images_feature.presentation

import com.example.trainingintive.RulesForTests
import com.example.trainingintive.TestSchedulersProvider
import com.example.trainingintive.dog_images_feature.data.repository.DogImageRepository
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.navigators.MainNavigator
import com.example.trainingintive.util.MainScreenEvent
import com.example.trainingintive.util.toErrorTextId
import io.mockk.*
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class DogImageViewModelTest : RulesForTests() {

    val sampleImageUrl = "https:\\/\\/images.dog.ceo\\/breeds\\/bulldog-french\\/n02108915_9666.jpg"
    val sampleDog = DogImageUrl(sampleImageUrl)
    val repository: DogImageRepository = mockk {
        every { getDogImageUrl() } returns Single.just(sampleDog)
        every { insert(any()) } returns Completable.complete()
    }
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
        val tested = DogImageViewModel(repository, schedulersForTests, mainNavigator)

        tested.dog.observeForever { result ->
            result shouldBeEqualTo sampleImageUrl
        }
    }

    @Test
    fun `after successfully download, dogimage should be inserted into local database`() {
        DogImageViewModel(repository, schedulersForTests, mainNavigator)

        verify { repository.insert(sampleDog) }
    }

    @Test
    fun `if repository returns error vm should send error event`() {
        every { repository.getDogImageUrl() } returns Single.error(exception)

        DogImageViewModel(repository, schedulersForTests, mainNavigator)

        verify { mainNavigator.sendEvent(ofType(MainScreenEvent.Error::class)) }
    }

    @Test
    fun `if repository returns error vm should call toErrorTextId method`() {
        every { repository.getDogImageUrl() } returns Single.error(exception)

        DogImageViewModel(repository, schedulersForTests, mainNavigator)

        verify { exception.toErrorTextId() }
    }
}
