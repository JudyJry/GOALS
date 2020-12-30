package com.example.test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat


class Welcome : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContentView(R.layout.activity_welcome)
        this.window.statusBarColor = ContextCompat.getColor(this,R.color.white)
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        Handler().postDelayed({
            val intent = Intent()
            intent.setClass(this, LoginUser::class.java)
            startActivity(intent)
            this.finish()
        }, 3000)
    }
}