package com.example.test.ui.sort

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.SortItem

class SortItemFragment(private var itemPos: Int) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val s = SortItem.s[itemPos]
        val root = inflater.inflate(R.layout.fragment_sort_item, container, false)
        val labelList: RecyclerView = root.findViewById(R.id.sort_label_list)
        labelList.layoutManager =
            LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
        @Suppress("UNCHECKED_CAST")
        labelList.adapter = SortItemLabelView(root.context, s["label"] as MutableList<String>)
        return root
    }
}