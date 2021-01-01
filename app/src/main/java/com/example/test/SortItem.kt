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

    private val ballLabel = mutableListOf("籃球","羽球","排球","桌球","網球")
    private val aerobicLabel = mutableListOf("拳擊","體操","跳繩","舞蹈")
    private val outdoorLabel = mutableListOf("跑步","單車","跑酷","快走")
    private val indoorLabel = mutableListOf("瑜珈","健身","攀岩","飛輪")
    private val waterLabel = mutableListOf("游泳","浮潛","跳水","水球","衝浪")

    val s = mutableListOf(
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Ball_c,
            "subtitle" to "本版提供各式球類技巧討論、經驗分享或球類運動傷害，都可在此發文討論～",
            "label" to ballLabel
        ),
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Aerobic_c,
            "subtitle" to "有關有氧運動技巧討論、經驗分享或球類運動傷害，都可在此發文討論～",
            "label" to aerobicLabel
        ),
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Outdoor_c,
            "subtitle" to "逆流而上，不畏風寒，一起挑戰自己的極限吧！",
            "label" to outdoorLabel
        ),
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Indoor_c,
            "subtitle" to "寒流來襲，你需要的就是溫暖的室內運動，都來討論吧！",
            "label" to indoorLabel
        ),
        mutableMapOf<String, Any>(
            "title" to R.string.Sort_Water_c,
            "subtitle" to "水上運動相關，都可在此發文討論。",
            "label" to waterLabel
        ),
    )


}