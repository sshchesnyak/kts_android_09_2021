package ru.iu3.sshchesnyak_kts_android_09_2021

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import ru.iu3.sshchesnyak_kts_android_09_2021.databinding.FragmentAuthorizationBinding
import ru.iu3.sshchesnyak_kts_android_09_2021.models.LoginViewModel


class LoginPage: Fragment(R.layout.fragment_authorization){

    private val viewModel: LoginViewModel by viewModels()

    private var binding: FragmentAuthorizationBinding?=null
    private var loginButton: Button ?= null
    private var emailInput: TextInputEditText?= null
    private var passwordInput: TextInputEditText?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val thisBinding = FragmentAuthorizationBinding.bind(view)
        binding = thisBinding
        loginButton = binding?.buttonLogin
        emailInput = binding?.inputLogin
        passwordInput = binding?.inputPassword
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //val loginButton = view?.findViewById<Button>(R.id.buttonLogin)
        //emailInput = view?.findViewById<TextInputEditText>(R.id.inputLogin);
        //passwordInput = view?.findViewById<TextInputEditText>(R.id.inputPassword)

        emailInput?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.checkValidity(emailInput?.text.toString(),passwordInput?.text.toString())
            }
        })

        passwordInput?.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                viewModel.checkValidity(emailInput?.text.toString(),passwordInput?.text.toString())
            }

        })

        viewModel.state.observe(viewLifecycleOwner, { state ->

            if (state.checkValid)
                loginButton?.setEnabled(true)
            else
                loginButton?.setEnabled(false)

        })

        loginButton?.setOnClickListener {
            findNavController().navigate(R.id.action_LoginFragment_to_ListPage)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        passwordInput = null
        emailInput = null
    }
}