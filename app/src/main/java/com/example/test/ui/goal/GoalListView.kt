package com.example.test.ui.goal

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.GoalItem
import com.example.test.R

class GoalListView(val context: Context?,
                   val item: MutableList<MutableMap<String, Any>>) :
    RecyclerView.Adapter<GoalListView.Holder>() {
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

    fun update(){
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val joinBtn: Button = itemView.findViewById(R.id.goal_join_button)
        private val title: TextView = itemView.findViewById(R.id.Goal_Title)
        private val subtitle: TextView = itemView.findViewById(R.id.Goal_Subtitle)
        private val time: TextView = itemView.findViewById(R.id.Goal_Time)
        private val member: TextView = itemView.findViewById(R.id.Goal_member)
        fun bindData(p: Int) {
            val g = GoalItem.item[p]
            val mem = g["member"].toString()+"/"+g["maxMember"].toString()
            title.text = g["title"] as String
            subtitle.text = g["subtitle"] as String
            time.text = g["time"] as String
            member.text = mem

            if (GoalItem.joined[p]) {
                joinBtn.setText(R.string.Goal_joined_c)
                joinBtn.setBackgroundResource(R.drawable.shape_cyan_button)
            }
        }
    }
}