package com.example.trainingintive.what_to_do_feature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trainingintive.databinding.FragmentActivitiesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ActivitiesFragment : Fragment() {

    @Inject
    lateinit var adapter: ActivityModelRxAdapter

    val activitiesViewModel: ActivitiesViewModel by viewModels()

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
        activitiesViewModel.activities.observe(
            viewLifecycleOwner,
            {
                adapter.submitData(lifecycle, it)
            }
        )
        return binding.root
    }
}
