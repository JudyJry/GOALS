package com.example.test

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
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
import com.google.firebase.database.FirebaseDatabase
import org.jetbrains.anko.startActivity


class MainActivity : AppCompatActivity() {
    lateinit var tbtitle: TextView
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.statusBarColor = ContextCompat.getColor(this, R.color.deep_cyan)
        this.window.decorView.systemUiVisibility = 0

        database =  FirebaseDatabase.getInstance()

        setSupportActionBar(findViewById(R.id.Toolbar))
        tbtitle = findViewById(R.id.toolbar_title)

        drawerLayout = findViewById(R.id.drawer_layout)
        val navViewDrawer: NavigationView = findViewById(R.id.nav_view)
        navViewDrawer.setNavigationItemSelectedListener(navigationListener)

        val navView: BottomNavigationView = findViewById(R.id.footer)
        navView.setOnNavigationItemSelectedListener(bottomNavigationListener)

        val listButton :ImageButton = findViewById(R.id.toolbar_list)
        listButton.setOnClickListener(listOnClick)

    }


    private val listOnClick = View.OnClickListener {
        drawerLayout.openDrawer(GravityCompat.START)
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

}


