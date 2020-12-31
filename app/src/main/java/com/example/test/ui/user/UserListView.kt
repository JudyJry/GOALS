package com.example.test.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class UserListView(
    _items: MutableList<MutableMap<String, Any>>?
) : RecyclerView.Adapter<UserListView.Holder>() {
    var items = _items
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (items != null) {
            holder.bindData(items!![position])
        }
    }

    override fun getItemCount(): Int {
        if (items != null) {
            return items!!.count()
        } else {
            return 0
        }
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val title: TextView = itemView.findViewById(R.id.user_Title)
        private val subtitle: TextView = itemView.findViewById(R.id.user_Subtitle)
        private val time: TextView = itemView.findViewById(R.id.user_Time)
        fun bindData(items: MutableMap<String, Any>) {
            title.text = items["title"] as String
            subtitle.text = items["subtitle"] as String
            time.text = items["time"] as String
        }
    }
}