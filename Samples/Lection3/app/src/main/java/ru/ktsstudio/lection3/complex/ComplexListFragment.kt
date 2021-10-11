package ru.ktsstudio.lection3.complex

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import kotlinx.android.synthetic.main.item_complex.*
import ru.ktsstudio.lection3.R
import ru.ktsstudio.lection3.databinding.FragmentListBinding
import ru.ktsstudio.lection3.models.ComplexItem
import ru.ktsstudio.lection3.models.ImageItem
import ru.ktsstudio.lection3.models.SimpleItem
import ru.ktsstudio.lection3.utils.autoCleared
import java.util.*

class ComplexListFragment : Fragment(R.layout.fragment_list) {

    private val binding: FragmentListBinding by viewBinding(FragmentListBinding::bind)
    private var complexAdapter: ComplexListAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        setDefaultItems()
    }

    private fun initList() {
        complexAdapter = ComplexListAdapter()
        with(binding.list) {
            val orientation = RecyclerView.VERTICAL
            adapter = complexAdapter
            layoutManager = LinearLayoutManager(context, orientation, false)
            addItemDecoration(DividerItemDecoration(context, orientation))
            setHasFixedSize(true)
        }
    }

    private fun setDefaultItems() {
        val defaultItems = List(20) {
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
        complexAdapter.setItems(defaultItems)
    }
}