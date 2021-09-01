package com.example.trainingintive.dog_images_feature.data.repository

import com.example.trainingintive.dog_images_feature.data.entity.DogImageUrlApiModel
import com.example.trainingintive.dog_images_feature.data.entity.DogImageUrlDb
import com.example.trainingintive.dog_images_feature.data.entity.toDatabaseEntity
import com.example.trainingintive.dog_images_feature.data.local.DogImageDao
import com.example.trainingintive.dog_images_feature.data.network.DogImageApiService
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.unmockkStatic
import io.mockk.verify
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import org.junit.After
import org.junit.Before
import org.junit.Test

class DogImageRepositoryImplTest {

    val dogApiService: DogImageApiService = mockk()
    val dogDao: DogImageDao = mockk()
    val tested = DogImageRepositoryImpl(dogApiService, dogDao)

    @Before
    fun start() {
        mockkStatic(DogImageUrl::toDatabaseEntity)
    }

    @After
    fun finish() {
        unmockkStatic(DogImageUrl::toDatabaseEntity)
    }

    @Test
    fun `api service should be called when getting url`() {
        every { dogApiService.getDogImage() } returns Single.just(mockk())
        every { dogDao.count() } returns Single.just(1)

        tested.getDogImageUrl().test()

        verify { dogApiService.getDogImage() }
    }

    @Test
    fun `count method should be called when getting url`() {
        val urlApiModel: DogImageUrlApiModel = mockk()
        every { dogApiService.getDogImage() } returns Single.just(urlApiModel)
        every { dogDao.count() } returns Single.just(1)

        tested.getDogImageUrl()

        verify { dogDao.count() }
    }

    @Test
    fun `get dog image url`() {
        val expected: DogImageUrl = mockk()
        val urlApiModel: DogImageUrlApiModel = mockk {
            every { toDomain(any()) } returns expected
        }
        every { dogApiService.getDogImage() } returns Single.just(urlApiModel)
        every { dogDao.count() } returns Single.just(1)

        val result = tested.getDogImageUrl().test()

        result.assertValue(expected)
    }

    @Test
    fun `get all urls should return list of domain models`() {
        val dogImageUrl: DogImageUrl = mockk()
        val dogImageUrlDb: DogImageUrlDb = mockk {
            every { toDomain() } returns dogImageUrl
        }
        val dbModels = listOf(dogImageUrlDb)
        val expected = listOf(dogImageUrl)
        every { dogDao.getAllImageUrls() } returns Flowable.just(dbModels)

        val result = tested.getAllImageUrls().test()

        result.assertValue(expected)
    }

    @Test
    fun `dao should be called when getting all urls`() {
        every { dogDao.getAllImageUrls() } returns Flowable.just(emptyList())

        tested.getAllImageUrls()

        verify { dogDao.getAllImageUrls() }
    }

    @Test
    fun `test insert`() {
        val valueToInsert: DogImageUrlDb = mockk()
        val urlDomain: DogImageUrl = mockk {
            every { toDatabaseEntity() } returns valueToInsert
        }
        every { dogDao.insert(any()) } returns Completable.complete()

        tested.insert(urlDomain)

        verify { dogDao.insert(valueToInsert) }
    }

    @Test
    fun `test update`() {
        val valueToUpdate: DogImageUrlDb = mockk()
        val urlDomain: DogImageUrl = mockk {
            every { toDatabaseEntity() } returns valueToUpdate
        }
        every { dogDao.update(any<DogImageUrlDb>()) } returns Completable.complete()

        tested.update(urlDomain)

        verify { dogDao.update(valueToUpdate) }
    }

    @Test
    fun `test remove`() {
        val valueToRemove: DogImageUrlDb = mockk()
        val urlDomain: DogImageUrl = mockk {
            every { toDatabaseEntity() } returns valueToRemove
        }
        every { dogDao.remove(any()) } returns Completable.complete()

        tested.remove(urlDomain)

        verify { dogDao.remove(valueToRemove) }
    }

    @Test
    fun `test update list`() {
        val dogImageUrlDb: DogImageUrlDb = mockk()
        val dogImageUrl: DogImageUrl = mockk {
            every { toDatabaseEntity() } returns dogImageUrlDb
        }
        val urls = listOf(dogImageUrl)
        val listToUpdate = listOf(dogImageUrlDb)
        every { dogDao.update(any<List<DogImageUrlDb>>()) } returns Completable.complete()

        tested.update(urls).test()

        verify { dogDao.update(listToUpdate) }
    }

    @Test
    fun `test changing positions`() {
        val dbModels: List<DogImageUrlDb> = listOf(
            mockk {
                every { position } returns 0
            },
            mockk {
                every { position } returns 1
            },
        )
        val models: List<DogImageUrl> = listOf(
            mockk(relaxed = true) {
                every { position } returns 0
                every { toDatabaseEntity() } returns dbModels[0]
            },
            mockk(relaxed = true) {
                every { position } returns 1
                every { toDatabaseEntity() } returns dbModels[1]
            },
        )
        val expected = listOf(dbModels[1], dbModels[0])
        every { dogDao.update(any<List<DogImageUrlDb>>()) } returns Completable.complete()

        tested.changeImagePosition(models, 0, 1).test()

        verify { dogDao.update(expected) }
    }
}
