package com.example.trainingintive.dog_images_feature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trainingintive.databinding.FragmentDogImagesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogImagesFragment : Fragment() {

    val dogImageViewModel: DogImageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDogImagesBinding.inflate(inflater, container, false)
        binding.dogImageViewmodel = dogImageViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }
}
