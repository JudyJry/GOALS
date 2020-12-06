package com.example.test.ui.goal

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.test.GoalItem
import com.example.test.R

class GoalItemFragment(private var itemPos: Int) : Fragment() {
    private lateinit var goalViewModel: GoalViewModel
    private lateinit var joinBtn: Button
    private lateinit var mText:String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        GoalItem.setItemPos(itemPos)
        goalViewModel = ViewModelProvider(this).get(GoalViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_goal_item, container, false)
        val title: TextView = root.findViewById(R.id.goal_item_title)
        val time: TextView = root.findViewById(R.id.goal_item_time)
        val author: TextView = root.findViewById(R.id.goal_item_author)
        val member: TextView = root.findViewById(R.id.goal_item_member)
        val context: TextView = root.findViewById(R.id.goal_item_context)
        val location: TextView = root.findViewById(R.id.goal_item_location)
        joinBtn = root.findViewById(R.id.goal_join_button)
        joinBtn.setOnClickListener(joinOnClick)

        goalViewModel.mTitle.observe(viewLifecycleOwner, { title.text = it })
        goalViewModel.mTime.observe(viewLifecycleOwner, { time.text = it })
        goalViewModel.mLocation.observe(viewLifecycleOwner, { location.text = it })
        goalViewModel.mAuthor.observe(viewLifecycleOwner, { author.text = it })
        goalViewModel.mMember.observe(viewLifecycleOwner, { member.text = it })
        goalViewModel.mContext.observe(viewLifecycleOwner, { context.text = it })
        goalViewModel.mJoined.observe(viewLifecycleOwner, {
            if (it) {
                joined()
            }
        })
        return root
    }

    private var joinOnClick = View.OnClickListener {
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定參加", joinClickOk)
            .setNeutralButton("取消", null)
            .show()
        val at: TextView = a.findViewById(R.id.act_alert_text)
        at.text = "確定參加此目標嗎？"
    }
    private var joinClickOk = DialogInterface.OnClickListener { _: DialogInterface, _: Int ->
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定", null)
            .show()
        val at: TextView = a.findViewById(R.id.act_alert_text)
        at.text = "參加成功"
        GoalItem.joined[itemPos] = true
        joined()
    }

    private fun joined() {
        joinBtn.text = resources.getString(R.string.Goal_cancel_c)
        joinBtn.setBackgroundResource(R.drawable.shape_cyan_button)
        joinBtn.setOnClickListener(cancelOnClick)
    }

    private var cancelOnClick = View.OnClickListener {
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定", cancelClickOk)
            .setNeutralButton("取消", null)
            .show()
        val at: TextView = a.findViewById(R.id.act_alert_text)
        at.text = "確定取消參加此目標嗎？"
    }
    private var cancelClickOk = DialogInterface.OnClickListener { _: DialogInterface, _: Int ->
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定", null)
            .show()
        val at: TextView = a.findViewById(R.id.act_alert_text)
        at.text = "取消成功"
        GoalItem.joined[itemPos] = false
        cancelJoined()
    }

    private fun cancelJoined() {
        joinBtn.text = resources.getString(R.string.Goal_join_c)
        joinBtn.setBackgroundResource(R.drawable.shape_orange_bottom)
        joinBtn.setOnClickListener(joinOnClick)
    }
}
