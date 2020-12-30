package com.example.test.ui.goal

import android.app.Activity.RESULT_CANCELED
import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.test.ChildActivity
import com.example.test.GoalItem
import com.example.test.R
import java.io.File
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
    private lateinit var imgButton: Button
    private lateinit var imgText: TextView
    private var isNull = true
    private lateinit var root : View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_goal_new, container, false)

        findView(root)

        startDate.setOnClickListener(startDateOnClick)
        endDate.setOnClickListener(endDateOnClick)
        newButton.setOnClickListener(newGoal)
        imgButton.setOnClickListener(imgOnClick)
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
        imgButton= root.findViewById(R.id.goal_img_button)
        imgText = root.findViewById(R.id.goal_new_img_text)
    }
    private val imgOnClick = OnClickListener {
        val intent = Intent(
            Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        )
        startActivityForResult(intent, 80)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 80 || resultCode == RESULT_OK){
            val uri: Uri = data?.data!!.normalizeScheme()
            val fileName = File(uri.path!!).name
            imgText.text = fileName
        }
        else if (resultCode == RESULT_CANCELED){
            Toast.makeText(root.context, "未選取檔案", Toast.LENGTH_SHORT).show()
        }
    }

    private val startDateOnClick = OnClickListener {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            root.context,
            { _, Year, Month, Day ->
                val m = Month.plus(1)
                val dateTime = "$Year/$m/$Day"
                startDate.text = dateTime
            }, year, month, day
        )
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.YEAR, minDate.get(Calendar.YEAR) + 1)
        dpd.datePicker.minDate = Calendar.getInstance().timeInMillis
        dpd.datePicker.maxDate = minDate.timeInMillis
        dpd.show()
    }
    private val endDateOnClick = OnClickListener {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            root.context,
            { _, Year, Month, Day ->
                val m = Month.plus(1)
                val dateTime = "$Year/$m/$Day"
                endDate.text = dateTime
            }, year, month, day
        )
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.YEAR, minDate.get(Calendar.YEAR) + 1)
        dpd.datePicker.minDate = Calendar.getInstance().timeInMillis
        dpd.datePicker.maxDate = minDate.timeInMillis
        dpd.show()
    }

    private val newGoal = OnClickListener {
        isHaveNull()
        if (!isNull){
            createNewGoal()
            val a = AlertDialog.Builder(root.context)
                .setView(R.layout.alert_act)
                .setPositiveButton("確定", newClickOk)
                .show()
            val at :TextView = a.findViewById(R.id.act_alert_text)
            at.text = "新增成功"
        }
    }
    private var newClickOk = DialogInterface.OnClickListener{ _: DialogInterface, _: Int ->
        activity?.onBackPressed()
        val intent =
            Intent(root.context, ChildActivity::class.java)
        intent.putExtra("PageName", "Goal_Item")
        intent.putExtra("GoalInt", GoalItem.joined.size - 1)
        startActivityForResult(intent, 0)
    }

    private fun createNewGoal(){
        val sd = startDate.text
        val ed = endDate.text
        val date = "$sd~$ed"
        val user = "USER"
        GoalItem.joined.add(true)
        GoalItem.created.add(true)
        GoalItem.item.add(
            mutableMapOf(
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
            )
        )
    }
    private fun isHaveNull(){
        val titleBool : Boolean = title.text.toString() == ""
        val subtitleBool : Boolean = title.text.toString() == ""
        val locationBool : Boolean = title.text.toString() == ""
        val maxMemberBool : Boolean = maxMember.text.toString() == ""
        val startDateBool : Boolean = startDate.text.toString() == ""
        val endDateBool : Boolean = endDate.text.toString() == ""
        isNull = titleBool || subtitleBool || locationBool || maxMemberBool || startDateBool || endDateBool
        val notNullToast ="請填寫項目"
        if (isNull) {
            Toast.makeText(root.context, notNullToast, Toast.LENGTH_SHORT).show()
        }
    }
}
