package com.example.trainingintive.viewmodels

import com.example.trainingintive.RulesForTests
import com.example.trainingintive.TestSchedulersProvider
import com.example.trainingintive.model.DogImageUrl
import com.example.trainingintive.repository.DogImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Test
import java.lang.Exception

class DogImageViewModelTest : RulesForTests() {

    val sampleImageUrl = "https:\\/\\/images.dog.ceo\\/breeds\\/bulldog-french\\/n02108915_9666.jpg"
    val sampleStatus = "success"
    val sampleDog = DogImageUrl(sampleImageUrl, sampleStatus)
    val repository: DogImageRepository = mockk {
        every { getDogImageUrl() } returns Single.just(sampleDog)
        every { insertIntoLocalDatabase(any()) } returns Completable.complete()
    }
    val schedulersForTests = TestSchedulersProvider()

    @Test
    fun `when viewmodel finish init, livedata should contain dog imiage url`() {
        val tested = DogImageViewModel(repository, schedulersForTests)

        tested.dog.observeForever { result ->
            result shouldBeEqualTo sampleImageUrl
        }
    }

    @Test
    fun `after successfully download, dogimage should be inserted into local database`() {
        DogImageViewModel(repository, schedulersForTests)

        verify { repository.insertIntoLocalDatabase(sampleDog) }
    }

    @Test
    fun `if repository returns error livedata should contain message`() {
        val errorMessage = "Unable to resolve host \"dog.ceo\": No address associated with hostname"
        every { repository.getDogImageUrl() } returns Single.error(Exception(errorMessage))

        val tested = DogImageViewModel(repository, schedulersForTests)

        tested.dog.observeForever { result ->
            result shouldBeEqualTo errorMessage
        }
    }
}
