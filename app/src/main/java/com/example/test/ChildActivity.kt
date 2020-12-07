package com.example.test

import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test.ui.act.ActItemFragment
import com.example.test.ui.goal.GoalItemFragment
import com.example.test.ui.goal.GoalNewFragment
import com.example.test.ui.setting.SettingFragment

class ChildActivity : AppCompatActivity() {
    lateinit var ctbTitle: TextView
    lateinit var backButton: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child)

        setSupportActionBar(findViewById(R.id.child_Toolbar))
        backButton = findViewById(R.id.child_toolbar_back)
        ctbTitle = findViewById(R.id.child_toolbar_title)
        backButton.setOnClickListener(backOnClick)
        selectPage()

    }

    private fun changePage(host: Int, page: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(host, page).commit()
    }

    private val backOnClick = View.OnClickListener {
        super.onBackPressed()
    }

    private fun selectPage(): Boolean {
        when (intent.getStringExtra("PageName")) {
            "Sort" -> {
                ctbTitle.setText(R.string.Main_Sort_c)
                changePage(R.id.child_nav_host_fragment, SettingFragment())
                return true
            }
            "Setting" -> {
                ctbTitle.setText(R.string.Main_Setting_c)
                changePage(R.id.child_nav_host_fragment, SettingFragment())
                return true
            }
            "Act_Item" -> {
                ctbTitle.setText(R.string.Main_Act_c)
                val pos: Int = intent.getIntExtra("ActInt", 0)
                changePage(R.id.child_nav_host_fragment, ActItemFragment(pos))
                return true
            }
            "Goal_Item" -> {
                ctbTitle.setText(R.string.Main_Goal_c)
                val pos: Int = intent.getIntExtra("GoalInt", 0)
                changePage(R.id.child_nav_host_fragment, GoalItemFragment(pos))
                return true
            }
            "Goal_New" -> {
                ctbTitle.setText(R.string.Goal_new_item)
                changePage(R.id.child_nav_host_fragment, GoalNewFragment())
                return true
            }
            else -> {
                return false
            }
        }
    }
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