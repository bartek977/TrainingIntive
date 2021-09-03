package com.example.trainingintive.di

import com.example.trainingintive.dog_images_feature.data.network.DogImageApiService
import com.example.trainingintive.what_to_do_feature.data.network.ActivityApiService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

private const val DOG_IMAGE_BASE_URL = "https://dog.ceo"
private const val ACTIVITY_BASE_URL = "https://www.boredapi.com"

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    fun provideRetrofit(moshi: Moshi, baseUrl: String): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    @DogImageUrlRetrofit
    fun provideRetrofitForDogImageApiService(moshi: Moshi): Retrofit = provideRetrofit(moshi, DOG_IMAGE_BASE_URL)

    @Provides
    @Singleton
    @ActivityRetrofit
    fun provideRetrofitForActivityApiService(moshi: Moshi): Retrofit = provideRetrofit(moshi, ACTIVITY_BASE_URL)

    @Singleton
    @Provides
    fun provideDogImageApiService(@DogImageUrlRetrofit retrofit: Retrofit): DogImageApiService {
        return retrofit.create(DogImageApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideActivityApiService(@ActivityRetrofit retrofit: Retrofit): ActivityApiService {
        return retrofit.create(ActivityApiService::class.java)
    }
}
