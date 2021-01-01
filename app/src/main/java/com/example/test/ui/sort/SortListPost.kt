package com.example.test.ui.sort

import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R


class SortListPost(list: List<String>) : RecyclerView.Adapter<SortListPost.Holder>() {
    private var mList = list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_sort_tab_post, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.count()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.post_title)
        private val more: ImageButton = itemView.findViewById(R.id.post_more)
        private val heart: ImageButton = itemView.findViewById(R.id.post_heart)
        private val heartText: TextView = itemView.findViewById(R.id.post_heart_text)
        private val save: ImageButton = itemView.findViewById(R.id.post_save)
        private val saveText: TextView = itemView.findViewById(R.id.post_save_text)
        fun bindData(i: String) {
            title.text = i
            more.setOnClickListener {
                val popupMenu = PopupMenu(itemView.context, more)
                val inflater: MenuInflater = popupMenu.menuInflater
                inflater.inflate(R.menu.sort_post_more, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener {
                    Toast.makeText(itemView.context, "已檢舉", Toast.LENGTH_SHORT).show()
                    return@setOnMenuItemClickListener true
                }
                popupMenu.show()
            }
            heart.setOnClickListener {
                heart.isSelected = !heart.isSelected
            }
            heartText.setOnClickListener {
                heart.isSelected = !heart.isSelected
            }
            save.setOnClickListener {
                save.isSelected = !save.isSelected
            }
            saveText.setOnClickListener {
                save.isSelected = !save.isSelected
            }
        }
    }
}