package com.example.test.ui.act

import android.app.AlertDialog
import android.content.ContentValues
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.test.ActItem
import com.example.test.R

class ActItemFragment(private var itemPos:Int) : Fragment() {
    private lateinit var actViewModel :ActViewModel
    private lateinit var joinBtn:Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(ContentValues.TAG, "Pos:$itemPos")
        actViewModel = ViewModelProvider(this).get(ActViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_act_item, container, false)
        val img :ImageView= root.findViewById(R.id.act_item_imageView)
        val title:TextView = root.findViewById(R.id.act_item_title)
        val time:TextView = root.findViewById(R.id.act_item_time)
        val location:TextView = root.findViewById(R.id.act_item_location)
        val cost:TextView = root.findViewById(R.id.act_item_cost)
        val count:TextView = root.findViewById(R.id.act_item_count)
        joinBtn = root.findViewById(R.id.act_join_button)
        joinBtn.setOnClickListener(joinOnClick)

        actViewModel.mImg.observe(viewLifecycleOwner, {
            img.setImageResource(it[itemPos])
        })
        actViewModel.mTitle.observe(viewLifecycleOwner, {
            title.text = resources.getString(it[itemPos])
        })
        actViewModel.mTime.observe(viewLifecycleOwner, {
            time.text = resources.getString(it[itemPos])
        })
        actViewModel.mLocation.observe(viewLifecycleOwner, {
            location.text = resources.getString(it[itemPos])
        })
        actViewModel.mCost.observe(viewLifecycleOwner, {
            cost.text = resources.getString(it[itemPos])
        })
        actViewModel.mCount.observe(viewLifecycleOwner, {
            count.text = resources.getString(it[itemPos])
        })
        actViewModel.mJoined.observe(viewLifecycleOwner, {
            if (it[itemPos]){joined()}
        })

        return root
    }

    private var joinOnClick = View.OnClickListener {
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定報名",joinClickOk)
            .setNeutralButton("取消",null)
            .show()
        val at :TextView = a.findViewById(R.id.act_alert_text)
        at.text = "確定報名活動嗎？"
    }
    private var joinClickOk = DialogInterface.OnClickListener{
            _: DialogInterface, _: Int ->
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定",null)
            .show()
        val at :TextView = a.findViewById(R.id.act_alert_text)
        at.text = "報名成功"
        ActItem.joined[itemPos]=true
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
        val at :TextView = a.findViewById(R.id.act_alert_text)
        at.text = "確定取消報名活動嗎？"
    }
    private var cancelClickOk = DialogInterface.OnClickListener{
            _: DialogInterface, _: Int ->
        val a = AlertDialog.Builder(this.context)
            .setView(R.layout.alert_act)
            .setPositiveButton("確定",null)
            .show()
        val at :TextView = a.findViewById(R.id.act_alert_text)
        at.text = "取消報名成功"
        ActItem.joined[itemPos]=false
        cancelJoined()
    }
    private fun cancelJoined() {
        joinBtn.text = resources.getString(R.string.Act_join_c)
        joinBtn.setBackgroundResource(R.drawable.shape_orange_bottom)
        joinBtn.setOnClickListener(joinOnClick)
    }
}

