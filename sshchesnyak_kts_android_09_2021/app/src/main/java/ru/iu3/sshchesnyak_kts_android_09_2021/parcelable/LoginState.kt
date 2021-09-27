package ru.iu3.sshchesnyak_kts_android_09_2021.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginState(
    val checkValid: Boolean
): Parcelable