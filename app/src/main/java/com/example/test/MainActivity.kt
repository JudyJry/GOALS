package com.example.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.IBinder
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.ContentView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.test.ui.act.ActFragment
import com.example.test.ui.goal.GoalFragment
import com.example.test.ui.home.HomeFragment
import com.example.test.ui.sort.SortFragment
import com.example.test.ui.user.UserFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tbtitle: TextView
    private lateinit var listButton: ImageButton
    private lateinit var checkButton: ImageButton
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navViewDrawer: NavigationView
    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.deep_cyan)
        this.window.decorView.systemUiVisibility = 0

        setVal()

        drawerLayout = findViewById(R.id.drawer_layout)
        navViewDrawer.setNavigationItemSelectedListener(navigationListener)
        navView.setOnNavigationItemSelectedListener(bottomNavigationListener)
        listButton.setOnClickListener(listOnClick)
        checkButton.setOnClickListener(checkOnClick)

    }

    private fun setVal() {
        tbtitle = findViewById(R.id.toolbar_title)
        listButton = findViewById(R.id.toolbar_list)
        checkButton = findViewById(R.id.toolbar_check)
        navViewDrawer = findViewById(R.id.nav_view)
        navView = findViewById(R.id.footer)
    }


    private val listOnClick = View.OnClickListener {
        drawerLayout.openDrawer(GravityCompat.START)
    }

    private val checkOnClick = View.OnClickListener {
        val intent =
            Intent(this@MainActivity, ChildActivity::class.java)
        intent.putExtra("PageName", "CheckIn")
        startActivityForResult(intent, 0)
    }

    @Suppress("UNUSED_PARAMETER")
    fun navListOnClick(view: View) {
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    fun changePage(host: Int, page: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(host, page).commit()
        drawerLayout.closeDrawer(GravityCompat.START)
    }

    private var navigationListener =
        object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.drawer_sort -> {
                        val navView: BottomNavigationView = findViewById(R.id.footer)
                        navView.selectedItemId = R.id.navigation_user
                        changePage(R.id.main_nav_host_fragment, SortFragment())
                        tbtitle.setText(R.string.Main_Sort_c)
                        return true
                    }
                    R.id.drawer_setting -> {
                        val intent =
                            Intent(this@MainActivity, ChildActivity::class.java)
                        intent.putExtra("PageName", "Setting")
                        startActivityForResult(intent, 0)
                        return true
                    }
                    R.id.drawer_logout -> {
                        finish()
                        startActivity<LoginUser>()
                        return true
                    }
                    else -> {
                        return false
                    }
                }
            }
        }

    private var bottomNavigationListener =
        object : BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.navigation_home -> {
                        tbtitle.setText(R.string.Main_Home_c)
                        changePage(R.id.main_nav_host_fragment, HomeFragment())
                        return true
                    }
                    R.id.navigation_goal -> {
                        tbtitle.setText(R.string.Main_Goal_c)
                        changePage(R.id.main_nav_host_fragment, GoalFragment())
                        return true
                    }
                    R.id.navigation_act -> {
                        tbtitle.setText(R.string.Main_Act_c)
                        changePage(R.id.main_nav_host_fragment, ActFragment())
                        return true
                    }
                    R.id.navigation_user -> {
                        tbtitle.setText(R.string.Main_User_c)
                        changePage(R.id.main_nav_host_fragment, UserFragment())
                        return true
                    }
                    else -> {
                        return false
                    }
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


