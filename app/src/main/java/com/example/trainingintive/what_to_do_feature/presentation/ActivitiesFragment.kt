package com.example.trainingintive.what_to_do_feature.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.MyApplication
import com.example.trainingintive.databinding.FragmentActivitiesBinding
import com.example.trainingintive.di.ViewModelFactory
import javax.inject.Inject

class ActivitiesFragment : Fragment() {

    @Inject
    lateinit var adapter: ActivityModelAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    val activitiesViewModel: ActivitiesViewModel by lazy { ViewModelProvider(this, viewModelFactory)[ActivitiesViewModel::class.java] }

    override fun onAttach(context: Context) {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        binding.apply {
            activitiesViewmodel = activitiesViewModel
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
        }
        return binding.root
    }
}
