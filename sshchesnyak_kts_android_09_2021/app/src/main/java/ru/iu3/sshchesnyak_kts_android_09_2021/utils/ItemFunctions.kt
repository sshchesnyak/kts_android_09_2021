package ru.iu3.sshchesnyak_kts_android_09_2021.utils

import java.text.SimpleDateFormat
import java.util.*

class ItemFunctions {
    fun setDate():String{
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val dateInDate = Calendar.getInstance().time
        return formatter.format(dateInDate)
    }
    fun alterVotes(direction:Int):Int{
        return when(direction){
            1->1
            -1->-1
            else->error("Unacceptable direction given!")
        }
    }
}