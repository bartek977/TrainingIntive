package com.example.trainingintive.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.MyApplication
import com.example.trainingintive.di.ViewModelFactory
import com.example.trainingintive.navigators.SIGN_IN_RESULT_CODE
import com.example.trainingintive.navigators.SplashNavigator
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var navigator: SplashNavigator

    lateinit var viewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        navigator.attachActivity(this)
        // TODO Use `by lazy` here
        viewModel = ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java]
    }

    override fun onDestroy() {
        super.onDestroy()
        navigator.detachActivityAndResetEvent()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == SIGN_IN_RESULT_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                viewModel.onSuccesLogin()
            } else {
                viewModel.onFailedLogin()
            }
        }
    }
}
