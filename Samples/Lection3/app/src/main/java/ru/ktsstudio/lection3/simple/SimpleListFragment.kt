package ru.ktsstudio.lection3.simple

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import ru.ktsstudio.lection3.R
import ru.ktsstudio.lection3.databinding.FragmentListBinding
import ru.ktsstudio.lection3.models.SimpleItem
import ru.ktsstudio.lection3.utils.autoCleared
import java.util.*

class SimpleListFragment : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    private var simpleAdapter: SimpleListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        setDefaultItems()
    }

    private fun initList() {
        simpleAdapter = SimpleListAdapter()
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = simpleAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)
            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun setDefaultItems() {
        val defaultItems = List(20) {
            val randomUUID = UUID.randomUUID()
            SimpleItem(
                title = "Самый обыкновенный заголовок",
                uuid = randomUUID,
                author = "Автор: Овчинников Максим"
            )
        }
        simpleAdapter.setItems(defaultItems)
    }
}