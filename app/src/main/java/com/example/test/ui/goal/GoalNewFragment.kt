package com.example.test.ui.goal

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.test.ChildActivity
import com.example.test.GoalItem
import com.example.test.R
import java.util.*

class GoalNewFragment : Fragment() {
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    private lateinit var title: EditText
    private lateinit var subtitle: EditText
    private lateinit var maxMember :EditText
    private lateinit var location: EditText
    private lateinit var context: EditText
    private lateinit var newButton: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_goal_new, container, false)

        findView(root)

        startDate.setOnClickListener(startDateOnClick)
        endDate.setOnClickListener(endDateOnClick)
        newButton.setOnClickListener(newGoal)
        return root
    }

    private fun findView(root: View) {
        newButton = root.findViewById(R.id.goal_new_button)
        title = root.findViewById(R.id.goal_new_title)
        subtitle = root.findViewById(R.id.goal_new_subtitle)
        maxMember = root.findViewById(R.id.goal_new_max_member)
        location = root.findViewById(R.id.goal_new_location)
        context = root.findViewById(R.id.goal_new_context)
        startDate = root.findViewById(R.id.goal_new_start_date)
        endDate = root.findViewById(R.id.goal_new_end_date)
    }

    private val startDateOnClick = View.OnClickListener {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireContext(),
            { _, Year, Month, Day ->
                val dateTime = "$Year/$Month/$Day"
                startDate.text = dateTime
            }, year, month, day
        )
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.YEAR, minDate.get(Calendar.YEAR) + 1)
        dpd.datePicker.minDate = Calendar.getInstance().timeInMillis
        dpd.datePicker.maxDate = minDate.timeInMillis
        dpd.show()
    }
    private val endDateOnClick = View.OnClickListener {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireContext(),
            { _, Year, Month, Day ->
                val dateTime = "$Year/$Month/$Day"
                endDate.text = dateTime
            }, year, month, day
        )
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.YEAR, minDate.get(Calendar.YEAR) + 1)
        dpd.datePicker.minDate = Calendar.getInstance().timeInMillis
        dpd.datePicker.maxDate = minDate.timeInMillis
        dpd.show()
    }

    private val newGoal = View.OnClickListener {
        if (!isHaveNull()){
            createNewGoal()
            val a = AlertDialog.Builder(requireContext())
                .setView(R.layout.alert_act)
                .setPositiveButton("確定",newClickOk)
                .show()
            val at :TextView = a.findViewById(R.id.act_alert_text)
            at.text = "新增成功"
        }
    }
    private var newClickOk = DialogInterface.OnClickListener{
            _: DialogInterface, _: Int ->
        activity?.onBackPressed()
        val intent =
            Intent(requireContext(), ChildActivity::class.java)
        intent.putExtra("PageName", "Goal_Item")
        intent.putExtra("GoalInt", GoalItem.joined.size-1)
        startActivityForResult(intent, 0)
    }

    private fun createNewGoal(){
        val sd = startDate.text
        val ed = endDate.text
        val date = "$sd~$ed"
        val user = "USER"
        GoalItem.joined.add(true)
        GoalItem.g.add(mutableMapOf(
            "author" to user,
            "context" to context.text.toString(),
            "location" to location.text.toString(),
            "maxMember" to maxMember.text.toString(),
            "member" to 1,
            "subtitle" to subtitle.text.toString(),
            "time" to date,
            "title" to title.text.toString(),
            "user" to R.drawable.goal_user_icon_5,
            "img" to R.drawable.goal_location_5
        ))
    }
    private fun isHaveNull(): Boolean {
        val titleBool : Boolean = title.text == null
        val subtitleBool : Boolean = title.text == null
        val locationBool : Boolean = title.text == null
        val maxMemberBool : Boolean = maxMember.text == null
        val startDateBool : Boolean = startDate.text == null
        val endDateBool : Boolean = endDate.text == null
        val isNull : Boolean = titleBool || subtitleBool || locationBool || maxMemberBool || startDateBool || endDateBool
        val notNullToast = ""
        if (isNull) {
            if (titleBool) {
                notNullToast.plus(resources.getString(R.string.Goal_new_title_c))
                notNullToast.plus("\n")
            }
            if (subtitleBool) {
                notNullToast.plus(resources.getString(R.string.Goal_new_subtitle_c))
                notNullToast.plus("\n")
            }
            if (locationBool) {
                notNullToast.plus(resources.getString(R.string.Goal_new_location_c))
                notNullToast.plus("\n")
            }
            if (maxMemberBool) {
                notNullToast.plus(resources.getString(R.string.Goal_new_max_member_c))
                notNullToast.plus("\n")
            }
            if (startDateBool) {
                notNullToast.plus("請輸入開始日期")
                notNullToast.plus("\n")
            }
            if (endDateBool) {
                notNullToast.plus("請輸入結束日期")
                notNullToast.plus("\n")
            }
            Toast.makeText(requireContext(), notNullToast, Toast.LENGTH_LONG).show()
            return true
        }
        else {return false}
    }
}
