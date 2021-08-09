package com.example.trainingintive.di

import android.content.Context
import com.example.trainingintive.dog_images_feature.presentation.DogImagesFragment
import com.example.trainingintive.presentation.MainActivity
import com.example.trainingintive.presentation.SplashActivity
import com.example.trainingintive.what_to_do_feature.di.RepositoryModule
import com.example.trainingintive.what_to_do_feature.presentation.ActivitiesFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class, RoomModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(fragment: ActivitiesFragment)
    fun inject(activity: SplashActivity)
    fun inject(fragment: DogImagesFragment)
    fun inject(activity: MainActivity)
}
