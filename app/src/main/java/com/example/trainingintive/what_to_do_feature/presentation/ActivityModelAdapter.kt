package com.example.trainingintive.what_to_do_feature.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingintive.R
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import javax.inject.Inject

class ActivityModelAdapter @Inject constructor() : RecyclerView.Adapter<ActivityModelAdapter.ItemViewHolder>() {

    var activities = listOf<ActivityModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_activity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.textView.text = activities[position].activity
    }

    override fun getItemCount(): Int = activities.size
}
