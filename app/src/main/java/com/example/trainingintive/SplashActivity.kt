package com.example.trainingintive

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.di.ViewModelFactory
import com.example.trainingintive.navigators.SplashNavigator
import com.example.trainingintive.viewmodels.SplashViewModel
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigator: SplashNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        navigator.attachActivity(this)
        val viewModel = ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detachActivity()
    }
}
