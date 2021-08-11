package com.example.trainingintive.dog_images_feature.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.trainingintive.MyApplication
import com.example.trainingintive.databinding.FragmentAlbumBinding
import com.example.trainingintive.di.ViewModelFactory
import javax.inject.Inject

class AlbumFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var adapter: AlbumAdapter

    val albumViewModel: AlbumViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[AlbumViewModel::class.java]
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
        val binding = FragmentAlbumBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            recyclerView.adapter = adapter
            viewmodel = albumViewModel
        }
        AlbumItemTouchHelper(
            onMove = albumViewModel::changeImagePosition,
            onSwiped = albumViewModel::removeImage
        ).attachToRecyclerView(binding.recyclerView)
        return binding.root
    }
}
