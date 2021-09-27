package ru.iu3.sshchesnyak_kts_android_09_2021

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import timber.log.Timber

class OnBoardingPage: Fragment(R.layout.fragment_onboarding) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        val startButton = view?.findViewById<Button>(R.id.LoginButtonView)

        startButton?.setOnClickListener {
            findNavController().navigate(R.id.action_OnBoardingFragment_to_LoginFragment)
        }
    }
}