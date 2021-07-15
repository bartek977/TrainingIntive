package com.example.trainingintive

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.trainingintive.viewmodels.SplashViewModel

class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.navigator.attachActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.navigator.detachActivity()
    }
}
