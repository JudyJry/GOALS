package com.example.test.ui.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.test.R

class UserFriendFragment : Fragment() {
    private lateinit var moreTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_user_friend, container, false)
        setVal(root)

        return root
    }

    private fun setVal(root: View) {
        moreTextView = root.findViewById(R.id.friend_now_more)
    }
}