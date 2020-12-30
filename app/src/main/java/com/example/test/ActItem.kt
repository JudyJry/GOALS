package com.example.test

object ActItem : ArrayList<Int>() {
    private var itemPos: Int = 0
    fun setItemPos(i: Int) {
        itemPos = i
    }

    fun getItemPos(): Int {
        return itemPos
    }
    val joined: MutableList<Boolean> = arrayListOf(
        false,
        false,
        false
    )
    val item = mutableListOf(
        mutableMapOf<String, Any>(
            "title" to "2020愛花蓮做環保全民ha健走",
            "subtitle" to "",
            "image" to R.drawable.banner_image1,
            "cost" to "免費",
            "location" to "花蓮太平洋公園曙光廣場–海濱街",
            "time" to "2020–10–17（15:30～18:30）",
            "count" to "此活動只有一個場次時間",
        ),
        mutableMapOf<String, Any>(
            "title" to "2020爺奶 - 一起到老 精彩美好",
            "subtitle" to "",
            "image" to R.drawable.banner_image2,
            "cost" to "報名費用：300元（✨年滿80歲以上長輩、中/低收身份者免費）",
            "location" to "屏東場–林後四林平地森林園區",
            "time" to "2020–11–14（08:00～11:00）",
            "count" to "此活動只有一個場次時間",
        ),
        mutableMapOf<String, Any>(
            "title" to "週三Night快樂打太極",
            "subtitle" to "",
            "image" to R.drawable.banner_image3,
            "cost" to "免費",
            "location" to "大安森林公園二號出口–新生南路二段1號",
            "time" to "2019–07–10～2035–07–25 每週三(20:00～21:00)",
            "count" to "此活動只有一個場次時間",
        ),
    )
}
