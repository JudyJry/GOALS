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
            "friendNum" to 3
        ),
        "whiteMagic" to mutableMapOf(
            "name" to "User",
            "password" to "wm133589",
            "friendNum" to 13
        ),
        "weme0110" to mutableMapOf(
            "name" to "User",
            "password" to "0110WeMe",
            "friendNum" to 7
        )
    )
}