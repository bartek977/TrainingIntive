package com.example.trainingintive.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.viewmodels.ActivitiesViewModel
import com.example.trainingintive.viewmodels.DogImageViewModel
import com.example.trainingintive.viewmodels.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun splashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ActivitiesViewModel::class)
    abstract fun activitiesViewModel(viewModel: ActivitiesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DogImageViewModel::class)
    abstract fun dogImageViewModel(viewModel: DogImageViewModel): ViewModel
}
