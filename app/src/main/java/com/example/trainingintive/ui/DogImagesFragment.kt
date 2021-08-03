package com.example.trainingintive.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.MyApplication
import com.example.trainingintive.databinding.FragmentDogImagesBinding
import com.example.trainingintive.di.ViewModelFactory
import com.example.trainingintive.viewmodels.DogImageViewModel
import javax.inject.Inject

class DogImagesFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val dogImageViewModel: DogImageViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[DogImageViewModel::class.java]
    }

    override fun onAttach(context: Context) {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

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
