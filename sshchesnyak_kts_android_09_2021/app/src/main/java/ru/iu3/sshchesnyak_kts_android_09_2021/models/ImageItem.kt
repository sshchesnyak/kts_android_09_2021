package ru.iu3.sshchesnyak_kts_android_09_2021.models

import android.media.Image
import ru.iu3.sshchesnyak_kts_android_09_2021.utils.ItemFunctions
import java.util.*

data class ImageItem(val id: UUID, val author:String, var date: String, var votes: Int) {
    private val commonFunc = ItemFunctions()
    fun setDate(){
        this.date=commonFunc.setDate()
    }
    fun alterVotes(direction:Int){
        this.votes+=commonFunc.alterVotes(direction)
    }
}