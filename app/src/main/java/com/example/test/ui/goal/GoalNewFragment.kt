package com.example.test.ui.goal

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.test.R
import java.util.*


class GoalNewFragment : Fragment() {
    private lateinit var startDate: TextView
    private lateinit var endDate: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_goal_new, container, false)
        startDate = root.findViewById(R.id.goal_new_start_date)
        endDate = root.findViewById(R.id.goal_new_end_date)
        startDate.setOnClickListener(dateOnClick)
        endDate.setOnClickListener(dateOnClick)
        return root
    }

    private val dateOnClick = View.OnClickListener {
        val calendar: Calendar = Calendar.getInstance()
        val year: Int = calendar.get(Calendar.YEAR)
        val month: Int = calendar.get(Calendar.MONTH)
        val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(
            requireContext(),
            { _, Year, Month, Day ->
                val dateTime = "$Year/$Month/$Day"
                startDate.setText(dateTime)
            }, year, month, day
        )
        val minDate = Calendar.getInstance()
        minDate.set(Calendar.YEAR,minDate.get(Calendar.YEAR)+1)
        dpd.datePicker.minDate = Calendar.getInstance().timeInMillis
        dpd.datePicker.maxDate = minDate.timeInMillis
        dpd.show()
    }
}
