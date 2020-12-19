package com.example.test.ui.user

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.*

class UserFragment : Fragment() {
    private lateinit var userNameText: TextView
    private lateinit var list: ArrayList<Int>
    private lateinit var tabView: RecyclerView
    private lateinit var itemList: RecyclerView
    private lateinit var itemAdapter: UserListView
    private lateinit var itemTextView: TextView
    private lateinit var itemListBg : LinearLayout
    private lateinit var friendButton : Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_user, container, false)
        setVal(root)

        tabView.layoutManager = LinearLayoutManager(root.context)
        tabView.adapter = UserTabView(list)
        tabView.addOnItemTouchListener(tabOnClick(root.context))

        itemList.layoutManager = LinearLayoutManager(root.context)
        itemList.adapter = itemAdapter

        itemListBg.layoutParams.height = 1891

        userNameText.text = UserInfo.users[UserInfo.getUser()]?.get("name") as String
        val friendText = "好友 ${UserInfo.users[UserInfo.getUser()]?.get("friendNum")}位"
        friendButton.text = friendText

        return root
    }

    private fun setVal(root: View){
        itemList = root.findViewById(R.id.user_tab_item_list)
        tabView = root.findViewById(R.id.user_tab)
        itemTextView = root.findViewById(R.id.user_noItem_text)
        itemListBg = root.findViewById(R.id.user_tab_item)
        friendButton = root.findViewById(R.id.user_friend_button)
        userNameText = root.findViewById(R.id.user_name)
        itemAdapter = UserListView(null)
        list = arrayListOf(
            R.string.User_MyGoals_c,
            R.string.User_MyGoals_Now_c,
            R.string.User_MyActs_Will_c,
            R.string.User_MyGoals_Past_c,
            R.string.User_MyActs_Past_c
        )
    }

    private fun setHeight(){
        if (tabView.measuredHeight > itemListBg.layoutParams.height) {
            itemListBg.layoutParams.height = tabView.measuredHeight
        }
    }

    private fun changeList(
        bool : MutableList<Boolean>,
        item : MutableList<MutableMap<String,Any>>)
    : MutableList<MutableMap<String, Any>> {
        var listItem : MutableList<MutableMap<String,Any>> = arrayListOf()
        for (i in bool.indices){
            if (bool[i]){
                listItem.add(item[i])
            }
        }
        if (listItem.isEmpty()) {itemTextView.visibility = View.VISIBLE}
        else {itemTextView.visibility = View.GONE}
        setHeight()
        return listItem
    }

    private fun tabOnClick(context: Context) = RecyclerItemClickListener(
        context, tabView, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                when (position) {
                    0 -> {
                        itemAdapter.items = changeList(GoalItem.created,GoalItem.item)
                    }
                    1 -> {
                        itemAdapter.items = changeList(GoalItem.joined,GoalItem.item)
                    }
                    2 -> {
                        itemAdapter.items = changeList(ActItem.joined,ActItem.item)
                    }
                    3 -> {
                        itemAdapter.items = null
                        itemTextView.visibility = View.VISIBLE
                        setHeight()
                    }
                    4 -> {
                        itemAdapter.items = null
                        itemTextView.visibility = View.VISIBLE
                        setHeight()
                    }
                }
            }

            override fun onItemLongClick(view: View?, position: Int) {
            }
        })
}