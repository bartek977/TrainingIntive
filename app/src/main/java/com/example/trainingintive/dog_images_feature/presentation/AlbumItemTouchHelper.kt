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
        private var dragFrom = -1
        private var dragTo = -1

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder,
        ): Boolean {
            dragFrom = viewHolder.bindingAdapterPosition
            dragTo = target.bindingAdapterPosition
            return true
        }

        override fun onSwiped(
            viewHolder: RecyclerView.ViewHolder,
            direction: Int,
        ) = onSwiped(viewHolder.bindingAdapterPosition)

        override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
            super.clearView(recyclerView, viewHolder)
            if (isDragDropEvent()) {
                move(dragFrom, dragTo)
            }
            dragTo = -1
            dragFrom = -1
        }

        private fun isDragDropEvent() = dragFrom != -1 && dragTo != -1 && dragFrom != dragTo

        private fun move(dragFrom: Int, dragTo: Int) {
            onMove(dragFrom, dragTo)
        }
    }
)
