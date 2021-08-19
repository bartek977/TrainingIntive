package com.example.trainingintive.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.trainingintive.di.ViewModelFactory
import com.example.trainingintive.navigators.Navigator
import javax.inject.Inject

abstract class BaseActivity<T : Navigator> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigator: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator.attachActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detachActivityAndResetEvent()
    }
}
