package ru.iu3.sshchesnyak_kts_android_09_2021.models

import ru.iu3.sshchesnyak_kts_android_09_2021.utils.ItemFunctions
import java.util.*

data class ArticleItem(val id: UUID, val author: String, val title: String,var date: String, var votes: Int) {
    private val commonFunc = ItemFunctions()
    fun setDate(){
        this.date=commonFunc.setDate()
    }
    fun alterVotes(direction:Int){
        this.votes+=commonFunc.alterVotes(direction)
    }
}