package com.example.test.ui.goal

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ChildActivity
import com.example.test.GoalItem
import com.example.test.R
import com.example.test.RecyclerItemClickListener
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GoalFragment : Fragment() {
    private lateinit var list: RecyclerView
    private lateinit var fab: FloatingActionButton
    private lateinit var searchEdit: EditText
    private lateinit var searchButton: TextView
    private lateinit var listAdapter: GoalListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_goal, container, false)
        setVal(root)
        list.layoutManager = LinearLayoutManager(this.context)
        list.adapter = listAdapter
        list.addOnItemTouchListener(
            RecyclerItemClickListener(
                root.context, list, object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent =
                            Intent(root.context, ChildActivity::class.java)
                        intent.putExtra("PageName", "Goal_Item")
                        intent.putExtra("GoalInt", position)
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
        return root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        listAdapter.update()
    }

    private fun setVal(root: View) {
        list = root.findViewById(R.id.Goal_list)
        fab = root.findViewById(R.id.goal_new_button)
        searchEdit = root.findViewById(R.id.search_edit_text)
        searchButton = root.findViewById(R.id.search_text_button)
        listAdapter = GoalListView(root.context,GoalItem.item)
    }
}