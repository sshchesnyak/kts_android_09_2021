package ru.ktsstudio.lection3.complex_delegates

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ktsstudio.lection3.R
import ru.ktsstudio.lection3.databinding.FragmentListBinding
import ru.ktsstudio.lection3.models.ComplexItem
import ru.ktsstudio.lection3.models.ImageItem
import ru.ktsstudio.lection3.models.LoadingItem
import ru.ktsstudio.lection3.models.SimpleItem
import ru.ktsstudio.lection3.utils.PaginationScrollListener
import ru.ktsstudio.lection3.utils.autoCleared
import java.util.*

class ComplexDelegatesListFragment : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    private var complexAdapter: ComplexDelegatesListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        loadMoreItems()
    }

    private fun initList() {
        complexAdapter = ComplexDelegatesListAdapter(
            // Sharing
            onSendEmail = { complexItem ->
                val intent = Intent(Intent.ACTION_SENDTO).apply {
                    data = Uri.parse("mailto:${complexItem.email}")
                    putExtra(Intent.EXTRA_SUBJECT, complexItem.title)
                }
                startActivity(intent)
            }
        )
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = complexAdapter
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
        when ((1..3).random()) {
            1 -> SimpleItem(
                title = "Самый обыкновенный заголовок",
                uuid = randomUUID,
                author = "Автор: Овчинников Максим"
            )
            2 -> ComplexItem(
                title = "Самый обыкновенный заголовок",
                subTitle = "Самый обыкновенный подзаголовок",
                email = "ktsstudio@mail.ru",
                uuid = randomUUID
            )
            3 -> ImageItem(
                id = it
            )
            else -> error("Wrong random number")
        }
    }

    private fun loadMoreItems() {
        val newItems = complexAdapter.items.toMutableList().apply {
            if (lastOrNull() is LoadingItem) {
                removeLastOrNull()
            }
        } + getDefaultItems() + LoadingItem()
        complexAdapter.items = newItems
        Log.d("Pagination", newItems.size.toString())
    }
}