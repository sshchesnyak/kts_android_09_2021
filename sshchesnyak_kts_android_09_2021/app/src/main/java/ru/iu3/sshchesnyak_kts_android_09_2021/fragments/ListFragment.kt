package ru.iu3.sshchesnyak_kts_android_09_2021.fragments

import android.content.Intent
import android.media.Image
import android.net.Uri
import android.os.Bundle
import ru.iu3.sshchesnyak_kts_android_09_2021.adapters.ListAdapter
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.iu3.sshchesnyak_kts_android_09_2021.R
import ru.iu3.sshchesnyak_kts_android_09_2021.databinding.FragmentListBinding
import ru.iu3.sshchesnyak_kts_android_09_2021.models.ArticleItem
import ru.iu3.sshchesnyak_kts_android_09_2021.models.ImageItem
import ru.iu3.sshchesnyak_kts_android_09_2021.models.LoadingItem
import ru.iu3.sshchesnyak_kts_android_09_2021.utils.ItemFunctions
import ru.iu3.sshchesnyak_kts_android_09_2021.utils.PaginationScrollListener
import ru.iu3.sshchesnyak_kts_android_09_2021.utils.autoCleared
import java.util.*

class ListFragment: Fragment(R.layout.fragment_list) {
    private val binding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    private var listAdapter: ListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        loadMoreItems()
    }

    private fun initList() {
        listAdapter = ListAdapter(
            // Sharing
            onOpenInBrowser = {Any ->
                val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://www.reddit.com"))
                startActivity(intent)
            }
        )
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = listAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)

            // Pagination
            addOnScrollListener(
                PaginationScrollListener(
                    layoutManager = layoutManager as LinearLayoutManager,
                    requestNextItems = ::loadMoreItems,
                    visibilityThreshold = 0
                )
            )

            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun getDefaultItems() = List(20) {
        val randomUUID = UUID.randomUUID()
        val cDate = ItemFunctions().setDate()
        when ((1..2).random()) {
            1 -> ArticleItem(
                id=randomUUID,
                author=getString(R.string.author_signature),
                title=getString(R.string.post_title),
                date=cDate,
                votes=5
            )
            2 -> ImageItem(
                id=randomUUID,
                author=getString(R.string.author_signature),
                date=cDate,
                votes=5
            )
            else -> error("Wrong random number")
        }
    }

    private fun loadMoreItems() {
        val newItems = listAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        } + getDefaultItems() + LoadingItem()
        listAdapter.items = newItems
    }
}