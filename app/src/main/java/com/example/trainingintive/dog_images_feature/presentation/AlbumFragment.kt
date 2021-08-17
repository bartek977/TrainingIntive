package com.example.trainingintive.dog_images_feature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.R
import com.example.trainingintive.di.ViewModelFactory
import javax.inject.Inject

class AlbumFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val albumViewModel: AlbumViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AlbumViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_album, container, false)
}
