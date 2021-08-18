package com.example.trainingintive.util

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import com.example.trainingintive.what_to_do_feature.presentation.ActivityModelAdapter

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
    if (adapter is ActivityModelAdapter) {
        (adapter as ActivityModelAdapter).activities = items as List<ActivityModel>
    }
}
