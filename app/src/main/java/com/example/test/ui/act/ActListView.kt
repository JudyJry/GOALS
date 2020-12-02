package com.example.test.ui.act

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ActItem
import com.example.test.R


class ActListView(
    val context: Context?, val item: MutableList<Int>
) : RecyclerView.Adapter<ActListView.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_act, parent, false)
        return Holder(itemView)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindData(item[position],position)
    }

    override fun getItemCount(): Int {
        return item.count()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val itemImg : ImageView= itemView.findViewById(R.id.act_imageView)
        private val joinBtn : Button = itemView.findViewById(R.id.act_join_button)
        fun bindData(i: Int, p: Int){
            itemImg.setImageResource(i)
            if (ActItem.joined[p]){
                joinBtn.setText(R.string.Act_joined_c)
                joinBtn.setBackgroundResource(R.drawable.shape_cyan_button)
            }
        }
    }
}
