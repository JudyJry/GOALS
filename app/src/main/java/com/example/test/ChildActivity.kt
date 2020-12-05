package com.example.test

import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.test.ui.act.ActItemFragment
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
                return true
            }
            else -> {
                return false
            }
        }
    }
}