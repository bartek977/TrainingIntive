package com.example.trainingintive.dog_images_feature.domain.usecase

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Flowable
import org.junit.Test

class GetAllUrlsUseCaseTest {

    val repository: DogImageRepository = mockk()
    val tested = GetAllUrlsUseCase(repository)

    @Test
    fun execute() {
        every { repository.getAllImageUrls() } returns Flowable.just(emptyList())

        tested.execute()

        verify { repository.getAllImageUrls() }
    }

    @Test
    fun `check returned value`() {
        val list = listOf(
            DogImageUrl("test1"),
            DogImageUrl("test2"),
            DogImageUrl("test3")
        )
        every { repository.getAllImageUrls() } returns Flowable.just(list)

        tested.execute()
            .test()
            .assertValue(list)
    }
}
