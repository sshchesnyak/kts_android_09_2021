package ru.iu3.sshchesnyak_kts_android_09_2021.adapters

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import ru.iu3.sshchesnyak_kts_android_09_2021.delegates.ArticleItemDelegate
import ru.iu3.sshchesnyak_kts_android_09_2021.delegates.ImageItemDelegate
import ru.iu3.sshchesnyak_kts_android_09_2021.delegates.PageLoadingDelegate
import ru.iu3.sshchesnyak_kts_android_09_2021.models.ArticleItem
import ru.iu3.sshchesnyak_kts_android_09_2021.models.ImageItem

class ListAdapter(
    onOpenInBrowser: (Any) -> Unit
) : AsyncListDifferDelegationAdapter<Any>(ComplexDiffCallback()) {

    init {
        delegatesManager
            .addDelegate(ArticleItemDelegate(onOpenInBrowser))
            .addDelegate(ImageItemDelegate(onOpenInBrowser))
            .addDelegate(PageLoadingDelegate())
    }

    class ComplexDiffCallback : DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(first: Any, second: Any): Boolean {
            return first.javaClass == second.javaClass && when (first) {
                is ArticleItem -> first.id == (second as ArticleItem).id
                is ImageItem -> first.id == (second as ImageItem).id
                else -> true
            }
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(first: Any, second: Any): Boolean = first == second
    }
}