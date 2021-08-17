package com.example.trainingintive.di

import com.example.trainingintive.data.repository.UserRepositoryImpl
import com.example.trainingintive.domain.repository.UserRepository
import dagger.Binds
import dagger.Module

@Module
abstract class UserRepositoryModule {

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}
