package com.example.trainingintive.dog_images_feature.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.trainingintive.R
import com.example.trainingintive.dog_images_feature.domain.model.DogImageUrl
import javax.inject.Inject

class AlbumAdapter @Inject constructor() : RecyclerView.Adapter<AlbumAdapter.ItemViewHolder>() {

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image_item)
    }

    var imageUrls = listOf<DogImageUrl>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.album_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val url = imageUrls[position].url
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        holder.image.load(imgUri)
    }

    override fun getItemCount() = imageUrls.size
}
