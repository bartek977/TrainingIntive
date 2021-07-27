package com.example.trainingintive.ui

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
import com.example.trainingintive.viewmodels.ActivitiesViewModel
import javax.inject.Inject

class ActivitiesFragment : Fragment() {

    @Inject
    lateinit var adapter: ActivityModelAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

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
        val viewModel = ViewModelProvider(this, viewModelFactory)[ActivitiesViewModel::class.java]
        binding.viewmodel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val recyclerView = binding.recyclerView
        val adapter = ActivityModelAdapter()
        viewModel.activities.observe(
            viewLifecycleOwner,
            {
                adapter.activities = it
            }
        )
        recyclerView.adapter = adapter
        return binding.root
    }
}
