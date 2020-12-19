package com.example.test.ui.user

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R

class UserTabView(private val list : ArrayList<Int>): RecyclerView.Adapter<UserTabView.Holder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_user_tab, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int {
        return list.count()
    }
    class Holder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private val textView : TextView = itemView.findViewById(R.id.user_tab_text)
        fun bindData(it : Int){
            textView.setText(it)
        }
    }
}