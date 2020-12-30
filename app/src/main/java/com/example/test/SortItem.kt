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

    private val ballLabel = mutableListOf("籃球","羽球","排球")

    val s = mutableListOf(
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Ball_c,
            "subtitle" to "",
            "label" to ballLabel
        ),
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Aerobic_c,
            "subtitle" to "",
            "label" to ballLabel
        ),
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Outdoor_c,
            "subtitle" to "",
            "label" to ballLabel
        ),
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Indoor_c,
            "subtitle" to "",
            "label" to ballLabel
        ),
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Water_c,
            "subtitle" to "",
            "label" to ballLabel
        ),
    )


}