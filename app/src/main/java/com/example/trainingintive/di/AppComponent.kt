package com.example.trainingintive.di

import com.example.trainingintive.SplashActivity
import com.example.trainingintive.ui.ActivitiesFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class, FirebaseModule::class])
interface AppComponent {

    fun inject(fragment: ActivitiesFragment)
    fun inject(activity: SplashActivity)
}
