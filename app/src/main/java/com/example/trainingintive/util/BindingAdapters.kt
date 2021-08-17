package com.example.trainingintive.adapter

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import com.example.trainingintive.dog_images_feature.presentation.AlbumAdapter

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@Suppress("UNCHECKED_CAST")
@BindingAdapter("data")
fun RecyclerView.bindData(items: List<Any>) {
    if (adapter is AlbumAdapter) {
        (adapter as AlbumAdapter).imageUrls = items as List<DogImageUrl>
    }
}
