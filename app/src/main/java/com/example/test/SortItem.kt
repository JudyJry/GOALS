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
        true,
        true,
        false,
        false,
        false
    )

    val s: MutableList<Int> = arrayListOf(
        R.string.Sort_Ball_c,
        R.string.Sort_Aerobic_c,
        R.string.Sort_Outdoor_c,
        R.string.Sort_Indoor_c,
        R.string.Sort_Water_c
    )
}