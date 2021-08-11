package com.example.trainingintive.dog_images_feature.presentation

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.DOWN
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.ItemTouchHelper.UP
import androidx.recyclerview.widget.RecyclerView

private const val ALL_DIRECTIONS = LEFT or RIGHT or DOWN or UP
private const val HORIZONTAL_DIRECTIONS = LEFT or RIGHT

class AlbumItemTouchHelper(
    private val onMove: (Int, Int) -> Unit,
    private val onSwiped: (Int) -> Unit,
) : ItemTouchHelper(
    object : ItemTouchHelper.SimpleCallback(
        ALL_DIRECTIONS,
        HORIZONTAL_DIRECTIONS
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            val from = viewHolder.bindingAdapterPosition
            val to = target.bindingAdapterPosition
            onMove(from, to)
            return true
        }

        override fun onSwiped(
            viewHolder: RecyclerView.ViewHolder,
            direction: Int,
        ) = onSwiped(viewHolder.bindingAdapterPosition)
    }
)
