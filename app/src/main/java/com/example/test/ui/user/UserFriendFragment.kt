package com.example.test.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.R
import com.example.test.UserInfo
import org.jetbrains.anko.imageResource

class UserFriendFragment : Fragment() {
    private lateinit var moreTextView: TextView
    private lateinit var friendImage: MutableList<ImageView>
    private lateinit var friendYetList: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_user_friend, container, false)
        setVal(root)
        setFriendImage()
        friendYetList.layoutManager = LinearLayoutManager(root.context)
        friendYetList.adapter = FriendYetListAdapter()
        return root
    }


    private fun setVal(root: View) {
        moreTextView = root.findViewById(R.id.friend_now_more)
        friendImage = mutableListOf(
            root.findViewById(R.id.friend_now_1),
            root.findViewById(R.id.friend_now_2),
            root.findViewById(R.id.friend_now_3),
            root.findViewById(R.id.friend_now_4),
            root.findViewById(R.id.friend_now_5),
            root.findViewById(R.id.friend_now_6),
        )
        friendYetList = root.findViewById(R.id.friend_yet_list)
    }

    private fun setFriendImage() {
        val friendNum: Int = UserInfo.users[UserInfo.getUser()]?.get("friendNum") as Int
        if (friendNum >= 6) {
            for (i in friendImage.indices) {
                friendImage[i].apply {
                    visibility = View.VISIBLE
                    imageResource = R.drawable.user_user_icon
                }
            }
        } else if (friendNum > 3) {
            for (i in IntRange(0, friendNum-1)) {
                friendImage[i].apply {
                    visibility = View.VISIBLE
                    imageResource = R.drawable.user_user_icon
                }
            }
            for (i in friendNum..5) {
                friendImage[i].visibility = View.INVISIBLE
            }
        } else if (friendNum != 0 && friendNum <= 3) {
            for (i in IntRange(0, friendNum-1)) {
                friendImage[i].apply {
                    visibility = View.VISIBLE
                    imageResource = R.drawable.user_user_icon
                }
            }
            for (i in 3..5) {
                friendImage[i].visibility = View.GONE
            }
        } else {for (i in IntRange(0,friendNum-1)) {
            friendImage[i].visibility = View.GONE
        }}
    }

    class FriendYetListAdapter :
        RecyclerView.Adapter<FriendYetListAdapter.Holder>() {
        private var mList: MutableList<Int> = arrayListOf(0,1,2,3,4)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_user_friend_yet, parent, false)
            return Holder(itemView)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bindData(mList[position])
        }

        override fun getItemCount(): Int = mList.count()

        class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item: TextView = itemView.findViewById(R.id.friend_yet_image)
            fun bindData(it: Int) {
                item.text = it.toString()
            }
        }
    }
}
