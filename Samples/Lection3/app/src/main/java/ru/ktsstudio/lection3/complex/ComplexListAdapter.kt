package ru.ktsstudio.lection3.complex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_complex.view.*
import kotlinx.android.synthetic.main.item_simple.view.*
import kotlinx.android.synthetic.main.item_simple.view.title
import ru.ktsstudio.lection3.R
import ru.ktsstudio.lection3.models.ComplexItem
import ru.ktsstudio.lection3.models.ImageItem
import ru.ktsstudio.lection3.models.SimpleItem
import ru.ktsstudio.lection3.simple.SimpleListAdapter
import java.lang.IllegalStateException

/**
 * @author Maxim Ovchinnikov on 31.07.2021.
 */
class ComplexListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items = emptyList<Any>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        fun getLayoutId(viewType: Int): Int {
            return when (viewType) {
                SIMPLE_ITEM_VIEW_ID -> R.layout.item_simple
                COMPLEX_ITEM_VIEW_ID -> R.layout.item_complex
                IMAGE_ITEM_VIEW_ID -> R.layout.item_image
                else -> throw error("Unknown view type")
            }
        }

        val itemView = LayoutInflater.from(parent.context)
            .inflate(getLayoutId(viewType), parent, false)
        val removeCallback = { item: Any, position: Int ->
            removeItem(item, position)
        }

        return when (viewType) {
            SIMPLE_ITEM_VIEW_ID -> SimpleViewHolder(itemView, removeCallback)
            COMPLEX_ITEM_VIEW_ID -> ComplexViewHolder(itemView, removeCallback)
            IMAGE_ITEM_VIEW_ID -> ImageViewHolder(itemView, removeCallback)
            else -> throw error("Unknown view type")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is SimpleItem -> SIMPLE_ITEM_VIEW_ID
            is ComplexItem -> COMPLEX_ITEM_VIEW_ID
            is ImageItem -> IMAGE_ITEM_VIEW_ID
            else -> error("Unknown item")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SimpleViewHolder -> holder.bind(items[position] as SimpleItem)
            is ComplexViewHolder -> holder.bind(items[position] as ComplexItem)
            is ImageViewHolder -> holder.bind(items[position] as ImageItem)
            else -> error("Unknown view holder")
        }
    }

    fun setItems(newItems: List<Any>) {
        items = newItems.toList()
        notifyDataSetChanged()
    }

    private fun removeItem(item: Any, position: Int) {
        val newItems = items.toMutableList().apply {
            remove(item)
        }
        items = newItems
        notifyItemRemoved(position)
    }

    class SimpleViewHolder(
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

    class ComplexViewHolder(
        override val containerView: View,
        onItemClick: (item: ComplexItem, position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: ComplexItem? = null

        init {
            containerView.setOnClickListener {
                currentItem?.let { item ->
                    onItemClick.invoke(item, adapterPosition)
                }
            }
        }

        fun bind(item: ComplexItem) = with(containerView) {
            currentItem = item
            title.text = item.title
            subtitle.text = item.subTitle
            email.text = item.email
            uuid.text = "Уникальный id: ${item.uuid}"
        }
    }

    class ImageViewHolder(
        override val containerView: View,
        onItemClick: (item: ImageItem, position: Int) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: ImageItem? = null

        init {
            containerView.setOnClickListener {
                currentItem?.let { item ->
                    onItemClick.invoke(item, adapterPosition)
                }
            }
        }

        fun bind(item: ImageItem) = with(containerView) {
            currentItem = item
            uuid.text = "Уникальный id: ${item.id}"
        }
    }

    companion object {
        private const val SIMPLE_ITEM_VIEW_ID = 1
        private const val COMPLEX_ITEM_VIEW_ID = 2
        private const val IMAGE_ITEM_VIEW_ID = 3
    }
}