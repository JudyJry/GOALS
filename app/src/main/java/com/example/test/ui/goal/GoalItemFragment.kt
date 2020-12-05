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

class GoalItemFragment(private var itemPos:Int) : Fragment() {
    private lateinit var goalViewModel : GoalViewModel
    private lateinit var joinBtn: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        goalViewModel = ViewModelProvider(this).get(GoalViewModel(itemPos)::class.java)
        val root =inflater.inflate(R.layout.fragment_goal_item, container, false)
        TODO()
        return root
    }
    private var joinOnClick = View.OnClickListener {
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定參加",joinClickOk)
            .setNeutralButton("取消",null)
            .show()
        val at : TextView = a.findViewById(R.id.act_alert_text)
        at.text = "確定參加此目標嗎？"
    }
    private var joinClickOk = DialogInterface.OnClickListener{
            _: DialogInterface, _: Int ->
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定",null)
            .show()
        val at : TextView = a.findViewById(R.id.act_alert_text)
        at.text = "參加成功"
        GoalItem.joined[itemPos]=true
        joined()
    }
    private fun joined() {
        joinBtn.text = resources.getString(R.string.Act_cancel_c)
        joinBtn.setBackgroundResource(R.drawable.shape_cyan_button)
        joinBtn.setOnClickListener(cancelOnClick)
    }
    private var cancelOnClick = View.OnClickListener {
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定",cancelClickOk)
            .setNeutralButton("取消",null)
            .show()
        val at : TextView = a.findViewById(R.id.act_alert_text)
        at.text = "確定取消參加此目標嗎？"
    }
    private var cancelClickOk = DialogInterface.OnClickListener{
            _: DialogInterface, _: Int ->
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定",null)
            .show()
        val at : TextView = a.findViewById(R.id.act_alert_text)
        at.text = "取消成功"
        GoalItem.joined[itemPos]=false
        cancelJoined()
    }
    private fun cancelJoined() {
        joinBtn.text = resources.getString(R.string.Act_join_c)
        joinBtn.setBackgroundResource(R.drawable.shape_orange_bottom)
        joinBtn.setOnClickListener(joinOnClick)
    }
}
