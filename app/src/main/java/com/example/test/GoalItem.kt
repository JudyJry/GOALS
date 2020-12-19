package com.example.test

object GoalItem : ArrayList<Int>() {
    private var itemPos: Int = 0
    fun setItemPos(i: Int) {
        itemPos = i
    }

    fun getItemPos(): Int {
        return itemPos
    }

    val joined: MutableList<Boolean> = arrayListOf(
        false,
        true,
        false,
        false,
        true
    )

    val created: MutableList<Boolean> = arrayListOf(
        false,
        false,
        false,
        false,
        true
    )

    val item = mutableListOf(
        mutableMapOf<String, Any>(
            "author" to "偉米",
            "context" to "我10/1號起將在大佳河濱公園舉辦大家一起路跑10km的活動，為期一個月的時間，我們的活動終指是想要大家一起堅持、團結，最後完成目標。\n" +
                    "若您對我辦的這個活動有興趣，歡迎您加入我們喲～",
            "location" to "大佳河濱公園",
            "maxMember" to 30,
            "member" to 15,
            "subtitle" to "大家一起每天跑10KM",
            "time" to "2020/10/01~2020/10/31",
            "title" to "每天跑10KM",
            "user" to R.drawable.goal_user_icon_1,
            "img" to R.drawable.goal_location_1
        ),
        mutableMapOf<String, Any>(
            "author" to "可可愛愛",
            "context" to "大家一起做到共同目標吧！",
            "location" to "不限制地點",
            "maxMember" to 30,
            "member" to 20,
            "subtitle" to "大家一起每天做一百個伏地挺身",
            "time" to "2020/10/15~2020/11/15",
            "title" to "每天做一百個伏地挺身",
            "user" to R.drawable.goal_user_icon_2,
            "img" to R.drawable.goal_location_5
        ),
        mutableMapOf<String, Any>(
            "author" to "一日千騎",
            "context" to "一個人騎，很累，但是，很多人騎，就不會了。",
            "location" to "各地腳踏車道",
            "maxMember" to 30,
            "member" to 9,
            "subtitle" to "每天騎10km",
            "time" to "2020/11/05~2020/12/05",
            "title" to "每天騎Ubike10km",
            "user" to R.drawable.goal_user_icon_3,
            "img" to R.drawable.goal_location_5
        ),
        mutableMapOf<String, Any>(
            "author" to "Yee醬",
            "context" to "都來都來yeeeee",
            "location" to "不限制地點",
            "maxMember" to 30,
            "member" to 7,
            "subtitle" to "大家一起每天仰臥起坐200下",
            "time" to "2020/11/07~2020/12/07",
            "title" to "每天仰臥起坐200下",
            "user" to R.drawable.goal_user_icon_4,
            "img" to R.drawable.goal_location_5
        ),
        mutableMapOf<String, Any>(
            "author" to "白魔女",
            "context" to "大家一起朝著小蠻腰努力吧！",
            "location" to "不限制地點",
            "maxMember" to 30,
            "member" to 1,
            "subtitle" to "每天60秒，三十天後小蠻腰",
            "time" to "2020/11/15~2020/12/15",
            "title" to "每日橋式60秒挑戰",
            "user" to R.drawable.goal_user_icon_5,
            "img" to R.drawable.goal_location_5
        )
    )
}