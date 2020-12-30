package com.example.test.ui.sort

import android.animation.*
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.test.R
import com.example.test.SortItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class SortItemFragment(private var itemPos: Int) : Fragment() {
    private var shortAnimationDuration: Int = 0
    private lateinit var labelList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val s = SortItem.s[itemPos]
        val root = inflater.inflate(R.layout.fragment_sort_item, container, false)
        labelList = root.findViewById(R.id.sort_label_list)
        val tabLayout: TabLayout = root.findViewById(R.id.sort_item_tab)
        val tabItem = arrayListOf(R.string.Sort_item_Hot_c, R.string.Sort_Item_New_c)
        val viewPager: ViewPager2 = root.findViewById(R.id.sort_item_viewPager)
        val tri: ImageButton = root.findViewById(R.id.sort_item_tri)

        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)

        labelList.layoutManager =
            LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
        @Suppress("UNCHECKED_CAST")
        labelList.adapter = SortItemLabelView(root.context, s["label"] as MutableList<String>)

        viewPager.adapter = SortItemPager(tabItem)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(tabItem[position])
        }.attach()


        tri.setOnClickListener {
            if (labelList.layoutParams.height == 0) {
                tri.apply {
                    rotation = 0f
                    animate()
                        .rotation(180f)
                        .setDuration(shortAnimationDuration.toLong())
                        .setListener(null)
                }
                listShow(labelList, 85)
            } else if (labelList.layoutParams.height == 85) {
                tri.apply {
                    rotation = 180f
                    animate()
                        .rotation(0f)
                        .setDuration(shortAnimationDuration.toLong())
                        .setListener(null)
                }
                listShow(labelList, 0)
            }
        }
        return root
    }

    private fun listShow(view: View, nh: Int) {
        val h = view.layoutParams.height
        val anim: ValueAnimator = ValueAnimator
            .ofInt(h, nh)
            .setDuration(shortAnimationDuration.toLong())
        anim.addUpdateListener { a ->
            val value = a.animatedValue
            view.layoutParams.height = value as Int
            view.requestLayout()
        }
        val animationSet = AnimatorSet()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(anim)
        animationSet.start()
    }
}