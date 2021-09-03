package com.example.trainingintive.dog_images_feature.domain.usecase

import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.domain.repository.DogImageRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import org.junit.Test

class ChangeImagePositionsUseCaseTest {

    val repository: DogImageRepository = mockk()
    val tested = ChangeImagePositionsUseCase(repository)

    @Test
    fun execute() {
        val list: List<DogImageUrl> = mockk()
        every { repository.changeImagePosition(any(), any(), any()) } returns Completable.complete()

        tested.execute(list, 0, 1)

        verify { repository.changeImagePosition(list, 0, 1) }
    }
}
