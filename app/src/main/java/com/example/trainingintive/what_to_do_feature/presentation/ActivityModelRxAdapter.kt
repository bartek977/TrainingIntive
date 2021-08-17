package com.example.trainingintive.what_to_do_feature.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.trainingintive.R
import com.example.trainingintive.what_to_do_feature.domain.model.ActivityModel
import javax.inject.Inject

class ActivityModelRxAdapter @Inject constructor() :
    PagingDataAdapter<ActivityModel, ActivityModelRxAdapter.ItemViewHolder>(
        Comparator
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)

        return ItemViewHolder(adapterLayout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.textView.text = it.activity
        }
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.item_activity)
    }

    object Comparator : DiffUtil.ItemCallback<ActivityModel>() {
        override fun areItemsTheSame(oldItem: ActivityModel, newItem: ActivityModel) =
            oldItem.activity == newItem.activity

        override fun areContentsTheSame(oldItem: ActivityModel, newItem: ActivityModel) =
            oldItem == newItem
    }
}
