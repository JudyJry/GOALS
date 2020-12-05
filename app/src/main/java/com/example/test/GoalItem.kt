package com.example.test

object GoalItem : ArrayList<Int>() {
    val joined: MutableList<Boolean> = arrayListOf(
        false,
        false,
        false,
        false,
        false
    )
    val g = mutableListOf(
        mapOf(
            "author" to "weme0110",
            "context" to "我會在10月1號開始在大佳河濱公園跑步，歡迎大家一起來努力運動！",
            "location" to "大佳河濱公園",
            "maxMember" to "30",
            "member" to "15",
            "subtitle" to "大家一起每天跑10KM",
            "time" to "2020/10/01~2020/10/31",
            "title" to "每天跑10KM"
        ),
        mapOf(
            "author" to "USER",
            "context" to "大家一起做到共同目標吧！",
            "location" to "不限制地點",
            "maxMember" to "30",
            "member" to "20",
            "subtitle" to "大家一起每天做一百個伏地挺身",
            "time" to "2020/10/15~2020/11/15",
            "title" to "每天做一百個伏地挺身"
        ),
        mapOf(
            "author" to "USER",
            "context" to "一個人騎，很累，但是，很多人騎，就不會了。",
            "location" to "各地腳踏車道",
            "maxMember" to "30",
            "member" to "9",
            "subtitle" to "每天騎10km",
            "time" to "2020/11/05~2020/12/05",
            "title" to "每天騎Ubike10km"
        ),
        mapOf(
            "author" to "USER",
            "context" to "都來都來喔",
            "location" to "不限制地點",
            "maxMember" to "30",
            "member" to "7",
            "subtitle" to "大家一起每天仰臥起坐200下",
            "time" to "2020/11/07~2020/12/07",
            "title" to "每天仰臥起坐200下"
        ),
        mapOf(
            "author" to "whiteMagic",
            "context" to "大家一起朝著小蠻腰努力吧！",
            "location" to "不限制地點",
            "maxMember" to "30",
            "member" to "1",
            "subtitle" to "每天60秒，三十天後小蠻腰",
            "time" to "2020/11/15~2020/12/15",
            "title" to "每日橋式60秒挑戰"
        )
    )
}