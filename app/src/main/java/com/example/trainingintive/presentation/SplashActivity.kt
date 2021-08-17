package com.example.trainingintive.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.MyApplication
import com.example.trainingintive.navigators.SIGN_IN_RESULT_CODE
import com.example.trainingintive.navigators.SplashNavigator

class SplashActivity : BaseActivity<SplashNavigator>() {

    val viewModel: SplashViewModel by lazy { ViewModelProvider(this, viewModelFactory)[SplashViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        viewModel.start()
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
