package ru.iu3.sshchesnyak_kts_android_09_2021

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Button
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import ru.iu3.sshchesnyak_kts_android_09_2021.model.LoginViewModel
import timber.log.Timber


class LoginPage: Fragment(R.layout.fragment_authorization){
    private var emailInput: TextInputEditText?= null
    private var passwordInput: TextInputEditText?= null

    private val viewModel: LoginViewModel by viewModels()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val loginButton = view?.findViewById<Button>(R.id.buttonLogin)



        emailInput = view?.findViewById<TextInputEditText>(R.id.inputLogin);
        passwordInput = view?.findViewById<TextInputEditText>(R.id.inputPassword)

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
            findNavController().navigate(R.id.action_LoginFragment_to_MainPage)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        passwordInput = null
        emailInput = null
    }

}