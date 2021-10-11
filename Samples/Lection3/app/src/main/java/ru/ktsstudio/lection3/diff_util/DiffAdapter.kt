package ru.ktsstudio.lection3.diff_util

import androidx.recyclerview.widget.RecyclerView

abstract class DiffAdapter<T : Any, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private var items = emptyList<T>()

    override fun getItemCount() = items.size

    fun getItems() = items

    fun setItems(newItems: List<T>) {
        val diffResult = getDiffCalculator(items, newItems).calculateDiff()
        items = newItems.toList()
        if (newItems.isEmpty()) {
            notifyDataSetChanged()
        } else {
            diffResult.dispatchUpdatesTo(this)
        }
    }

    protected fun getItem(position: Int): T = items[position]

    abstract fun getDiffCalculator(oldItems: List<T>, newItems: List<T>): DiffCalculator<T>
}
