package com.example.trainingintive.di

import android.content.Context
import com.example.trainingintive.SplashActivity
import com.example.trainingintive.ui.ActivitiesFragment
import com.example.trainingintive.ui.DogImagesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class, FirebaseModule::class, RoomModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: ActivitiesFragment)
    fun inject(activity: SplashActivity)
    fun inject(fragment: DogImagesFragment)
}
