package com.example.trainingintive

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.trainingintive.repository.RepositoryImpl
import com.example.trainingintive.repository.network.NetworkRepository
import com.example.trainingintive.viewmodels.MainViewModel
import com.example.trainingintive.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var networkRepository: NetworkRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewmodel: MainViewModel by viewModels {
            MainViewModelFactory(
                RepositoryImpl(
                    networkRepository
                )
            )
        }
    }
}
