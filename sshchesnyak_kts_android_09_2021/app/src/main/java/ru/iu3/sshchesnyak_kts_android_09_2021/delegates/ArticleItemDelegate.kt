package ru.iu3.sshchesnyak_kts_android_09_2021.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_article.view.*
import kotlinx.android.synthetic.main.signature_container.view.*
import ru.iu3.sshchesnyak_kts_android_09_2021.R
import ru.iu3.sshchesnyak_kts_android_09_2021.models.ArticleItem

class ArticleItemDelegate(private val onItemClick: (item: ArticleItem) -> Unit) : AbsListItemAdapterDelegate<Any, Any, ArticleItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is ArticleItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_article, parent, false)
        return ViewHolder(itemView,onItemClick)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as ArticleItem)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: ArticleItem) -> Unit
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        private var currentItem: ArticleItem? = null

        init {
            containerView.setOnClickListener { currentItem?.let(onItemClick) }
        }

        fun bind(item: ArticleItem) = with(containerView) {
            currentItem = item
            article_title.text=item.title
            article_author.text=item.author
            date_published.text=item.date
            votes.text=item.votes.toString()
            upvote_btn.setOnClickListener{
                item.alterVotes(1)
                votes.text=item.votes.toString()
            }
            downvote_btn.setOnClickListener{
                item.alterVotes(-1)
                votes.text=item.votes.toString()
            }
        }
    }
}