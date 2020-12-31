package com.example.test

object UserInfo : ArrayList<Int>(){
    private var user: String = ""
    fun setUser(i: String) {
        user = i
    }
    fun getUser(): String {
        return user
    }
    val users : MutableMap<String,MutableMap<String,Any>> = mutableMapOf(
        "USER" to mutableMapOf(
            "name" to "User",
            "password" to "user",
            "friendNum" to 3,
            "image" to R.color.deep_cyan
        ),
        "whiteMagic" to mutableMapOf(
            "name" to "白魔女",
            "password" to "wm133589",
            "friendNum" to 13,
            "image" to R.drawable.user_user_icon
        ),
        "weme0110" to mutableMapOf(
            "name" to "偉米",
            "password" to "0110WeMe",
            "friendNum" to 7,
            "image" to R.drawable.goal_user_icon_1
        )
    )

    val friend : MutableList<MutableMap<String,Any>> = mutableListOf(
        mutableMapOf(
            "name" to "User222",
            "image" to R.color.deep_orange
        ),
        mutableMapOf(
            "name" to "白魔女",
            "image" to R.drawable.user_user_icon
        ),
        mutableMapOf(
            "name" to "偉米",
            "image" to R.drawable.goal_user_icon_1
        ),
        mutableMapOf(
            "name" to "美霞",
            "image" to R.drawable.goal_user_icon_2
        ),
        mutableMapOf(
            "name" to "尼優名",
            "image" to R.drawable.goal_user_icon_3
        ),
        mutableMapOf(
            "name" to "克里夫",
            "image" to R.drawable.goal_user_icon_4
        )
    )
}