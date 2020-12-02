package com.example.test.ui.goal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.test.GoalItem
import com.example.test.R

class GoalListView(val context: Context?, val item: MutableList<Int>)
    : RecyclerView.Adapter<GoalListView.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_goal, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(position)
    }

    override fun getItemCount(): Int {
        return item.count()
    }
    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val joinBtn : Button = itemView.findViewById(R.id.act_join_button)
        fun bindData(p: Int){
            if (GoalItem.joined[p]){
                joinBtn.setText(R.string.Goal_joined_c)
                joinBtn.setBackgroundResource(R.drawable.shape_cyan_button)
            }
        }
    }
}
