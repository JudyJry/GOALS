package com.example.test.ui.setting

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.test.R
import com.google.android.material.navigation.NavigationView

class SettingFragment : Fragment() {
    private lateinit var root : View
    private lateinit var navView : NavigationView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_setting, container, false)
        navView = root.findViewById(R.id.setting_nav)
        navView.setNavigationItemSelectedListener(navigationListener)
        return root
    }
    private var navigationListener =
        object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when (item.itemId) {
                    R.id.setting_person -> {
                        Toast.makeText(root.context,"點擊了個人設定",Toast.LENGTH_SHORT).show()
                        return true
                    }
                    R.id.setting_friend -> {
                        Toast.makeText(root.context,"點擊了好友設定",Toast.LENGTH_SHORT).show()
                        return true
                    }
                    R.id.setting_private -> {
                        Toast.makeText(root.context,"點擊了隱私設定",Toast.LENGTH_SHORT).show()
                        return true
                    }
                    R.id.setting_notification -> {
                        Toast.makeText(root.context,"點擊了提醒設定",Toast.LENGTH_SHORT).show()
                        return true
                    }
                    else -> {
                        return false
                    }
                }
            }
        }

}