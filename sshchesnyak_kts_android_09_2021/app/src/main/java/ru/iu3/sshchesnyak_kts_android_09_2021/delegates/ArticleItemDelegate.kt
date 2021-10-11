package ru.iu3.sshchesnyak_kts_android_09_2021.delegates

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import ru.iu3.sshchesnyak_kts_android_09_2021.R
import ru.iu3.sshchesnyak_kts_android_09_2021.databinding.ItemArticleBinding
import ru.iu3.sshchesnyak_kts_android_09_2021.models.ArticleItem
import ru.iu3.sshchesnyak_kts_android_09_2021.utils.bindingInflate
import ru.iu3.sshchesnyak_kts_android_09_2021.utils.inflate

class ArticleItemDelegate(private val onItemClick: (item: ArticleItem) -> Unit) : AbsListItemAdapterDelegate<Any, Any, ArticleItemDelegate.ViewHolder>() {

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is ArticleItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val itemView = parent.inflate(R.layout.item_article)
        val inflateArticle = parent.bindingInflate(ItemArticleBinding::inflate)
        return ViewHolder(itemView,onItemClick,inflateArticle)
    }

    override fun onBindViewHolder(item: Any, viewHolder: ViewHolder, payloads: MutableList<Any>) {
        viewHolder.bind(item as ArticleItem)
    }

    inner class ViewHolder(
        override val containerView: View,
        private val onItemClick: (item: ArticleItem) -> Unit,
        private var Article: ItemArticleBinding
    ) : RecyclerView.ViewHolder(Article.root), LayoutContainer {

        private var currentItem: ArticleItem? = null

        init {
            Article.element.setOnClickListener { currentItem?.let(onItemClick) }
            Article.signature.upvoteBtn.setOnClickListener{
                currentItem?.alterVotes(1)
                Article.signature.votes.text=currentItem?.votes.toString()
            }
            Article.signature.downvoteBtn.setOnClickListener{
                currentItem?.alterVotes(-1)
                Article.signature.votes.text=currentItem?.votes.toString()
            }
        }

        fun bind(item: ArticleItem) = with(containerView) {
            currentItem = item
            Article.articleTitle.text=item.title
            Article.signature.articleAuthor.text=item.author
            Article.signature.datePublished.text=item.date
            Article.signature.votes.text=item.votes.toString()
        }
    }
}