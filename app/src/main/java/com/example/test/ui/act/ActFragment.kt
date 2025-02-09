package com.example.test.ui.act

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.test.ActItem
import com.example.test.ChildActivity
import com.example.test.R
import com.example.test.RecyclerItemClickListener

class ActFragment : Fragment() {
    private lateinit var list: RecyclerView
    private lateinit var listAdapter : ActListView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_act, container, false)
        setVal(root)
        list.layoutManager = LinearLayoutManager(this.context)
        list.adapter = listAdapter
        list.addOnItemTouchListener(
            RecyclerItemClickListener(
                root.context, list, object : RecyclerItemClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        val intent =
                            Intent(root.context, ChildActivity::class.java)
                        intent.putExtra("PageName", "Act_Item")
                        intent.putExtra("ActInt", position)
                        startActivityForResult(intent, 0)
                    }

                    override fun onItemLongClick(view: View?, position: Int) {
                    }
                })
        )

        return root
    }

    private fun setVal(root: View) {
        list = root.findViewById(R.id.act_list)
        listAdapter = ActListView(root.context, ActItem.item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        listAdapter.update()
    }

}