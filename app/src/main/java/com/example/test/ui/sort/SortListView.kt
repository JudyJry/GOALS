package com.example.test.ui.sort

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.SortItem
import com.example.test.R

class SortListView(
    val context: Context, val item: MutableList<MutableMap<String, Any>>
) : RecyclerView.Adapter<SortListView.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sort, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(item[position]["title"] as Int,position)
    }

    override fun getItemCount(): Int {
        return item.count()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemText : TextView= itemView.findViewById(R.id.sort_name)
        private val likeBtn : ImageButton = itemView.findViewById(R.id.sort_like)
        fun bindData(i: Int, p: Int){
            itemText.setText(i)
            likeBtn.isSelected = SortItem.like[p]
        }
    }
}
