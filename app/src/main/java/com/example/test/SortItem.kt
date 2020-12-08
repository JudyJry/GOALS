package com.example.test

object SortItem : ArrayList<Int>() {
    private var itemPos: Int = 0
    fun setItemPos(i: Int) {
        itemPos = i
    }

    fun getItemPos(): Int {
        return itemPos
    }

    val like: MutableList<Boolean> = arrayListOf(
        false,
        false,
        false,
        false,
        false
    )
}