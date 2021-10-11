package ru.iu3.sshchesnyak_kts_android_09_2021.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.signature_container.view.*
import ru.iu3.sshchesnyak_kts_android_09_2021.R
import ru.iu3.sshchesnyak_kts_android_09_2021.databinding.ItemImageBinding
import ru.iu3.sshchesnyak_kts_android_09_2021.models.ImageItem
import ru.iu3.sshchesnyak_kts_android_09_2021.utils.bindingInflate
import ru.iu3.sshchesnyak_kts_android_09_2021.utils.inflate


class ImageItemDelegate(private val onItemClick: (item: ImageItem) -> Unit) : AbsListItemAdapterDelegate<Any, Any, ImageItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is ImageItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = parent.inflate(R.layout.item_image)
        val inflateImage = parent.bindingInflate(ItemImageBinding::inflate)
        return ViewHolder(itemView,onItemClick,inflateImage)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as ImageItem)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: ImageItem) -> Unit,
        private var Image: ItemImageBinding
    ) : RecyclerView.ViewHolder(Image.root), LayoutContainer {

        private var currentItem: ImageItem? = null

        init {
            Image.element.setOnClickListener { currentItem?.let(onItemClick) }
            Image.signature.upvoteBtn.setOnClickListener{
                currentItem?.alterVotes(1)
                Image.signature.votes.text=currentItem?.votes.toString()
            }
            Image.signature.downvoteBtn.setOnClickListener{
                currentItem?.alterVotes(-1)
                Image.signature.votes.text=currentItem?.votes.toString()
            }
        }

        fun bind(item: ImageItem) = with(containerView) {
            currentItem = item
            Image.signature.articleAuthor.text=item.author
            Image.signature.datePublished.text=item.date
            Image.signature.votes.text=item.votes.toString()
        }
    }
}