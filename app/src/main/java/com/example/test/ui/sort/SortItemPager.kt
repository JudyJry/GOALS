package com.example.test.ui.sort

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class SortItemPager(private val tabItem: ArrayList<Int>) : RecyclerView.Adapter<SortItemPager.PagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sort_tab_pager, parent, false)
        return PagerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return tabItem.size
    }
    class PagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val listView : RecyclerView = itemView.findViewById(R.id.sort_item_listView)
        fun bindData(position: Int) {
            val list : List<String> = if (position == 0) {
                arrayListOf(
                    "初學者學籃球會很難嗎？",
                    "排球志工招募中",
                    "台北室內運動場有哪些？",
                    "左屁股左大腿劇痛",
                    "球類運動不好的人可以打系上球隊嗎"
                )
            } else {
                arrayListOf(
                    "左屁股左大腿劇痛",
                    "球類運動不好的人可以打系上球隊嗎",
                    "排球志工招募中",
                    "台北室內運動場有哪些？",
                    "初學者學籃球會很難嗎？"
                )
            }
            listView.layoutManager = LinearLayoutManager(itemView.context)
            listView.adapter = SortListPost(list)
        }
    }
}