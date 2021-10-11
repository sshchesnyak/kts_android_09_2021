package ru.iu3.sshchesnyak_kts_android_09_2021

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import ru.iu3.sshchesnyak_kts_android_09_2021.databinding.FragmentOnboardingBinding

class OnBoardingPage: Fragment(R.layout.fragment_onboarding) {

    private var binding: FragmentOnboardingBinding?=null
    private var startButton: Button ?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val thisBinding = FragmentOnboardingBinding.bind(view)
        binding = thisBinding
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        startButton = binding?.LoginButtonView
        //val startButton = view?.findViewById<Button>(R.id.LoginButtonView)
        startButton?.setOnClickListener {
            findNavController().navigate(R.id.action_OnBoardingFragment_to_LoginFragment)
        }
    }
}