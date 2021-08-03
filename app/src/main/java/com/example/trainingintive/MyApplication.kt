package com.example.trainingintive

import android.app.Application
import com.example.trainingintive.di.AppComponent
import com.example.trainingintive.di.DaggerAppComponent

class MyApplication : Application() {

    val appComponent: AppComponent by lazy { DaggerAppComponent.factory().create(applicationContext) }
}
