package com.example.trainingintive.di

import com.example.trainingintive.MainActivity
import com.example.trainingintive.ui.ActivitiesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {

    fun inject(fragment: ActivitiesFragment)
    fun inject(activity: MainActivity)
}
