package ru.iu3.sshchesnyak_kts_android_09_2021.models

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.iu3.sshchesnyak_kts_android_09_2021.parcelable.LoginState

class LoginViewModel : ViewModel() {
    private val mutableState = MutableLiveData(LoginState(checkValid = false))

    val state: LiveData<LoginState>
        get() = mutableState

    fun checkValidity(emailInput: String, passwordInput: String) {
        val validEmail = Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()
        val validPassword = passwordInput.length >= 8
        if (validEmail && validPassword){
            mutableState.value = LoginState(true)
        }
        else mutableState.value = LoginState(false)
    }
}