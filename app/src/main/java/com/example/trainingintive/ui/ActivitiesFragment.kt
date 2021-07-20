package com.example.trainingintive.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trainingintive.MyApplication
import com.example.trainingintive.databinding.FragmentActivitiesBinding
import com.example.trainingintive.repository.RepositoryImpl
import com.example.trainingintive.viewmodels.ActivitiesViewModel
import com.example.trainingintive.viewmodels.ActivitiesViewModelFactory
import javax.inject.Inject

class ActivitiesFragment : Fragment() {

    @Inject
    lateinit var repository: RepositoryImpl

    @Inject
    lateinit var adapter: ActivityModelAdapter

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
        val viewModel: ActivitiesViewModel by viewModels { ActivitiesViewModelFactory(repository) }
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
