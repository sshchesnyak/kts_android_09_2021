package ru.iu3.sshchesnyak_kts_android_09_2021.delegates

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import kotlinx.android.extensions.LayoutContainer
import ru.iu3.sshchesnyak_kts_android_09_2021.R
import ru.iu3.sshchesnyak_kts_android_09_2021.models.LoadingItem

class PageLoadingDelegate :
    AbsListItemAdapterDelegate<LoadingItem, Any, PageLoadingDelegate.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup): Holder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_loading_page, parent, false)
        return Holder(itemView)
    }

    override fun isForViewType(item: Any, items: MutableList<Any>, position: Int): Boolean {
        return item is LoadingItem
    }

    override fun onBindViewHolder(
        item: LoadingItem,
        holder: Holder,
        payloads: MutableList<Any>
    ) {
    }

    class Holder(
        override val containerView: View
    ) : RecyclerView.ViewHolder(containerView), LayoutContainer
}