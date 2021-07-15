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
import javax.inject.Inject

class ActivitiesFragment : Fragment() {

    @Inject
    lateinit var adapter: ActivityModelAdapter

    private val viewModel: ActivitiesViewModel by viewModels()

    override fun onAttach(context: Context) {
        (requireActivity().application as MyApplication).appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentActivitiesBinding.inflate(inflater, container, false)
        val recyclerView = binding.recyclerView
        adapter.activities = viewModel.activities
        recyclerView.adapter = adapter
        return binding.root
    }
}
