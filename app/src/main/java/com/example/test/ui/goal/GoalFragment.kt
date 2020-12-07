package com.example.test.ui.goal

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ChildActivity
import com.example.test.GoalItem
import com.example.test.R
import com.example.test.RecyclerItemClickListenr
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GoalFragment : Fragment() {
    private lateinit var list: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var searchEdit: EditText
    private lateinit var searchButton: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_goal, container, false)
        list = root.findViewById(R.id.Goal_list)
        fab = root.findViewById(R.id.goal_new_button)
        searchEdit = root.findViewById(R.id.search_edit_text)
        searchButton = root.findViewById(R.id.search_text_button)
        list.layoutManager = LinearLayoutManager(this.context)
        list.adapter = GoalListView(root.context, GoalItem.g)
        list.addOnItemTouchListener(
            RecyclerItemClickListenr(
                root.context, list, object : RecyclerItemClickListenr.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent =
                            Intent(root.context, ChildActivity::class.java)
                        intent.putExtra("PageName", "Goal_Item")
                        intent.putExtra("GoalInt", position)
                        Log.d(ContentValues.TAG, "Pos:$position")
                        startActivityForResult(intent, 0)
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                    }
                })
        )
        fab.setOnClickListener {
            val intent = Intent(root.context, ChildActivity::class.java)
            intent.putExtra("PageName", "Goal_New")
            startActivityForResult(intent, 0)
        }
        /*searchEdit.setOnFocusChangeListener { _: View, b: Boolean ->
            if (b) {
                Log.d("onFocus", "searchEdit:$b")
                searchButton.visibility = View.VISIBLE
            } else {
                Log.d("onFocus", "searchEdit:$b")
                searchButton.visibility = View.INVISIBLE
            }
        }*/
        return root
    }
}