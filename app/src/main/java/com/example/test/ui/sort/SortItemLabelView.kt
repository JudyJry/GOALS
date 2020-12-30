package com.example.test.ui.sort

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class SortItemLabelView(
    val context: Context, val item: MutableList<String>
) : RecyclerView.Adapter<SortItemLabelView.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sort_label, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(item[position])
    }

    override fun getItemCount(): Int {
        return item.count()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val button : Button = itemView.findViewById(R.id.sort_item_label_item)
        fun bindData(it : String){
            button.text = it
        }
    }
}
