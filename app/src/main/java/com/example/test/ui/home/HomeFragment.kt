package com.example.test.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.test.ActItem
import com.example.test.R
import com.example.test.ui.user.UserFragment

class HomeFragment : Fragment() {
    private lateinit var goalMoreButton : TextView
    private lateinit var banner : ViewPager2
    private lateinit var bannerAdapter: HomeBannerAdapter
    private lateinit var list: List<Int>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        setVal(root)
        bannerAdapter.setList(list)
        banner.adapter = bannerAdapter
        goalMoreButton.setOnClickListener(toPage(UserFragment()))

        return root
    }

    private fun setVal(root: View){
        goalMoreButton = root.findViewById(R.id.home_goal_more)
        banner = root.findViewById(R.id.home_banner)
        bannerAdapter = HomeBannerAdapter()
        list = arrayListOf(
            ActItem.item[0]["image"] as Int,
            ActItem.item[1]["image"] as Int,
            ActItem.item[2]["image"] as Int,
        )
    }

    private fun toPage(page : Fragment) = View.OnClickListener{
        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        transaction.replace(R.id.main_nav_host_fragment, page)
        transaction.disallowAddToBackStack()
        transaction.commit()
    }
}
