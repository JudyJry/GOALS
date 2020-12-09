package com.example.test.ui.sort

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ChildActivity
import com.example.test.R
import com.example.test.RecyclerItemClickListener
import com.example.test.SortItem
import org.jetbrains.anko.image

class SortFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_sort, container, false)
        val list: RecyclerView = root.findViewById(R.id.sort_list)
        list.layoutManager = LinearLayoutManager(root.context)
        list.adapter = SortListView(root.context, SortItem.s)
        list.addOnItemTouchListener(
            RecyclerItemClickListener(
                root.context, list, object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent =
                            Intent(root.context, ChildActivity::class.java)
                        intent.putExtra("PageName", "Sort_Item")
                        intent.putExtra("SortInt", position)
                        startActivityForResult(intent, 0)
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                        val like: ImageButton = view!!.findViewById(R.id.sort_like)
                        if (SortItem.like[position]) {
                            like.setImageResource(R.drawable.ic_heart2)
                            SortItem.like[position] = false
                        }
                        else if (!SortItem.like[position]) {
                            like.setImageResource(R.drawable.ic_heart1)
                            SortItem.like[position] = true
                        }
                    }
                })
        )
        return root
    }
}