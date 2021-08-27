package com.example.trainingintive.dog_images_feature.domain.usecase

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Single
import org.junit.Test

class GetDogImageUrlUseCaseTest {

    val dogImageUrl =
        DogImageUrl("https:\\/\\/images.dog.ceo\\/breeds\\/bulldog-french\\/n02108915_9666.jpg", 0)
    val repository: DogImageRepository = mockk()
    val tested = GetDogImageUrlUseCase(repository)

    @Test
    fun execute() {
        every { repository.getDogImageUrl() } returns Single.just(dogImageUrl)

        tested.execute()

        verify { repository.getDogImageUrl() }
    }

    @Test
    fun `check returned value from repository`() {
        every { repository.getDogImageUrl() } returns Single.just(dogImageUrl)

        val result = tested.execute().test()

        result.assertValue(dogImageUrl)
    }
}
