package com.example.trainingintive.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trainingintive.databinding.FragmentActivitiesBinding

class ActivitiesFragment : Fragment() {

    private val viewModel: ActivitiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerView
        val adapter = ActivityModelAdapter()
        adapter.activities = viewModel.activities
        recyclerView.adapter = adapter
        return binding.root
    }
}
