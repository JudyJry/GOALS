package com.example.test.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.example.test.ActItem
import com.example.test.ChildActivity
import com.example.test.R

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, {
            textView.text = resources.getString(it)
        })

        val banner = root.findViewById<ViewPager2>(R.id.banner)
        val homeBannerAdapter = HomeBannerAdapter()
        homeBannerAdapter.setList(ActItem.List)
        banner.adapter = homeBannerAdapter
        banner.setOnClickListener {
            val intent =
                Intent(root.context, ChildActivity::class.java)
            intent.putExtra("PageName", "Act_Item")
            intent.putExtra("ActInt", banner.currentItem)
            startActivityForResult(intent, 0)
        }

        return root
    }
}
