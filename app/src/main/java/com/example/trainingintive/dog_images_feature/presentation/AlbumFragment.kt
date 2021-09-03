package com.example.trainingintive.dog_images_feature.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.trainingintive.databinding.FragmentAlbumBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AlbumFragment : Fragment() {

    @Inject
    lateinit var adapter: AlbumAdapter

    val albumViewModel: AlbumViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
            viewmodel = albumViewModel
        }
        AlbumItemTouchHelper(
            onMove = albumViewModel::changeImagePositionAndUpdateDb,
            onSwiped = albumViewModel::removeImage
        ).attachToRecyclerView(binding.recyclerView)
        return binding.root
    }
}
