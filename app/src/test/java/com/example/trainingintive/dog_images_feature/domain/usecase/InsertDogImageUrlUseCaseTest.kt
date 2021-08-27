package com.example.trainingintive.dog_images_feature.domain.usecase

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import org.junit.Test

class InsertDogImageUrlUseCaseTest {

    val dogImageUrl =
        DogImageUrl("https:\\/\\/images.dog.ceo\\/breeds\\/bulldog-french\\/n02108915_9666.jpg")
    val repository: DogImageRepository = mockk()
    val tested = InsertDogImageUrlUseCase(repository)

    @Test
    fun execute() {
        every { repository.insert(any()) } returns Completable.complete()

        tested.execute(dogImageUrl)

        verify { repository.insert(dogImageUrl) }
    }
}
