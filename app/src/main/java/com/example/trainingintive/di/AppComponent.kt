package com.example.trainingintive.di

import com.example.trainingintive.ui.ActivitiesFragment
import dagger.Component

@Component
interface AppComponent {

    fun inject(fragment: ActivitiesFragment)
}
