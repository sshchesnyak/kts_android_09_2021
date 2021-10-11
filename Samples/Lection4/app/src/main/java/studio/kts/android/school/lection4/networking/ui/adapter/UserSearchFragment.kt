package studio.kts.android.school.lection4.networking.ui.adapter

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import studio.kts.android.school.lection4.R
import studio.kts.android.school.lection4.databinding.FragmentUserSearchBinding
import studio.kts.android.school.lection4.networking.presentation.UserListViewModel
import studio.kts.android.school.lection4.utils.autoCleared

class UserSearchFragment : Fragment(R.layout.fragment_user_search) {

    private val viewModel: UserListViewModel by viewModels()

    //private val mybind by viewBinding(FragmentUserSearchBinding::bind)

    private val binding by viewBinding(FragmentUserSearchBinding::bind)
    private var userAdapter: UserAdapter by autoCleared()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initList()
        bindViewModel()
    }

    private fun initList() {
        userAdapter = UserAdapter()
        binding.userList.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun bindViewModel() {
        viewModel.userList.observe(viewLifecycleOwner, Observer { userAdapter.items = it })
        viewModel.isLoading.observe(viewLifecycleOwner, Observer { enableControls(it.not()) })
        binding.searchButton.setOnClickListener {
            viewModel.search(
                query = binding.searchInput.text.toString()
            )
        }
    }

    private fun enableControls(enable: Boolean) = with(binding) {
        searchInput.isEnabled = enable
        searchButton.isEnabled = enable
    }
}
