package ru.ktsstudio.lection3.simple

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_simple.view.*
import ru.ktsstudio.lection3.R
import ru.ktsstudio.lection3.models.SimpleItem

class SimpleListAdapter : RecyclerView.Adapter<SimpleListAdapter.ViewHolder>() {

    private var items = emptyList<SimpleItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_simple, parent, false)
        return ViewHolder(itemView) { item, position ->
            removeItem(item, position)
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(newItems: List<SimpleItem>) {
        items = newItems.toList()
        notifyDataSetChanged()
    }

    private fun removeItem(item: SimpleItem, position: Int) {
        val newItems = items.toMutableList().apply {
            remove(item)
        }
        items = newItems
        notifyItemRemoved(position)
    }

    class ViewHolder(
        override val containerView: View,
        onItemClick: (item: SimpleItem, position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: SimpleItem? = null

        init {
            containerView.setOnClickListener {
                currentItem?.let { item ->
                    onItemClick.invoke(item, adapterPosition)
                }
            }
        }

        fun bind(item: SimpleItem) = with(containerView) {
            currentItem = item
            title.text = item.title
            randomNumSubtitle.text = "Уникальный id: ${item.uuid}"
            author.text = item.author
        }
    }
}