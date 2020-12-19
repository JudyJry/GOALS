package com.example.test

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class LoginUser : AppCompatActivity() {
    private lateinit var edId: EditText
    private lateinit var edPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        actionBar?.hide()
        setContentView(R.layout.activity_login)
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        this.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        val loginButton : Button = findViewById(R.id.login_button)
        val googleButton : Button = findViewById(R.id.login_Google_button)
        val signUpButton : TextView = findViewById(R.id.login_Sign_up)
        loginButton.setOnClickListener(loginUser)
        googleButton.setOnClickListener {
            loginSuccess
            UserInfo.setUser("USER") }
        signUpButton.setOnClickListener{
            loginSuccess
            UserInfo.setUser("USER") }
        loginVal()
    }


    private fun loginVal() {
        edId = findViewById(R.id.login_Username)
        edPassword = findViewById(R.id.login_Password)
    }

    private val loginUser = View.OnClickListener{
        val id = edId.text.toString()
        val password = edPassword.text.toString()
        UserInfo.setUser(id)
        if (password == UserInfo.users[UserInfo.getUser()]?.get("password")) {
            toast("登入成功")
            startActivity<MainActivity>()
            this.finish()
        }
        else{
            toast("登入失敗")
        }
        edId.text.clear()
        edPassword.text.clear()
    }


    private val loginSuccess = View.OnClickListener{
        toast("登入成功")
        startActivity<MainActivity>()
        this.finish()
    }

    //touch exit softInput
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (isShouldHideInput(v, ev)) {
                hideSoftInput(v!!.windowToken)
            }
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun isShouldHideInput(v: View?, event: MotionEvent): Boolean {
        if (v != null && v is EditText) {
            val l = intArrayOf(0, 0)
            v.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + v.getHeight()
            val right = (left
                    + v.getWidth())
            if (!(event.x > left && event.x < right && event.y > top && event.y < bottom)) {
                return true
            }
        }
        return false
    }

    private fun hideSoftInput(token: IBinder?) {
        if (token != null) {
            val im: InputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(
                token,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}
