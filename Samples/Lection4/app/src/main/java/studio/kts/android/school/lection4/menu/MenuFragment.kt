package studio.kts.android.school.lection4.menu

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import studio.kts.android.school.lection4.R
import studio.kts.android.school.lection4.databinding.FragmentMenuBinding

class MenuFragment: Fragment(R.layout.fragment_menu) {

    private val binding by viewBinding(FragmentMenuBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            basicCoroutines.setOnClickListener {
                findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToBasicCoroutinesFragment())
            }

            cancelationAndError.setOnClickListener {
                findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToErrorCancelCoroutineFragment())
            }

            network.setOnClickListener {
                findNavController().navigate(MenuFragmentDirections.actionMenuFragmentToUserSearchFragment())
            }
        }
    }



}