package com.example.test

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CheckInFragment : Fragment() {
    private var shortAnimationDuration: Int = 0
    private var goalListHeight: Int = 0
    private lateinit var goalButton: ImageButton
    private lateinit var goalTextView: TextView
    private lateinit var goalList: RecyclerView
    private lateinit var locateButton: ImageButton
    private lateinit var locateTextView: TextView
    private lateinit var locateList: RecyclerView
    private lateinit var checkButton: Button
    private lateinit var picture:ImageView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_check_in, container, false)
        setVal(root)
        val gList = setList(GoalItem.joined, GoalItem.item, "title")
        goalListHeight = 60.toPx*gList.count()
        goalButton.setOnClickListener(goalButtonOnClick)
        goalList.layoutManager = LinearLayoutManager(root.context)
        goalList.adapter =
            GoalListAdapter(gList)
        locateList.layoutManager =
            LinearLayoutManager(root.context, LinearLayoutManager.HORIZONTAL, false)
        locateList.adapter =
            LocationListAdapter(setList(GoalItem.joined, GoalItem.item, "location"))

        goalList.addOnItemTouchListener(goalItemSelect(root.context))
        locateList.addOnItemTouchListener(locateItemSelect(root.context))
        checkButton.setOnClickListener {
            Toast.makeText(root.context,"打卡成功",Toast.LENGTH_SHORT).show()
            activity?.onBackPressed()
        }
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 81)
        return root
    }

    private fun setVal(root: View) {
        goalButton = root.findViewById(R.id.check_arrow_goal)
        goalTextView = root.findViewById(R.id.check_text_goal)
        goalList = root.findViewById(R.id.check_list_goal)
        locateButton = root.findViewById(R.id.check_arrow_location)
        locateTextView = root.findViewById(R.id.check_text_location)
        locateList = root.findViewById(R.id.check_list_location)
        shortAnimationDuration = resources.getInteger(android.R.integer.config_shortAnimTime)
        checkButton = root.findViewById(R.id.check_button)
        picture = root.findViewById(R.id.check_pic)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 81 && resultCode == Activity.RESULT_OK){
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            picture.setImageBitmap(imageBitmap)
        }
        else if (resultCode == Activity.RESULT_CANCELED){
            activity?.onBackPressed()
        }
    }

    private val goalButtonOnClick = View.OnClickListener {
        if (goalList.layoutParams.height == 0) {
            goalButton.apply {
                rotation = 180f
                animate()
                    .rotation(270f)
                    .setDuration(shortAnimationDuration.toLong())
                    .setListener(null)
            }
            listShow(goalList, goalListHeight)
        } else {
            goalButton.apply {
                rotation = 270f
                animate()
                    .rotation(180f)
                    .setDuration(shortAnimationDuration.toLong())
                    .setListener(null)
            }
            listShow(goalList, 0)
        }
    }


    private fun listShow(view: View, nh: Int) {
        val h = view.layoutParams.height
        val anim: ValueAnimator = ValueAnimator
            .ofInt(h, nh)
            .setDuration(shortAnimationDuration.toLong())
        anim.addUpdateListener { a ->
            val value = a.animatedValue
            view.layoutParams.height = value as Int
            view.requestLayout()
        }
        val animationSet = AnimatorSet()
        animationSet.interpolator = AccelerateDecelerateInterpolator()
        animationSet.play(anim)
        animationSet.start()
    }

    private fun setList(
        bool: MutableList<Boolean>,
        item: MutableList<MutableMap<String, Any>>,
        s: String
    ): MutableList<String> {
        var listItem: MutableList<String> = arrayListOf()
        for (i in bool.indices) {
            if (bool[i]) {
                listItem.add(item[i][s] as String)
            }
        }
        return listItem
    }

    class GoalListAdapter(list: MutableList<String>) :
        RecyclerView.Adapter<GoalListAdapter.Holder>() {
        private var mList: MutableList<String> = list
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_check_goal, parent, false)
            return Holder(itemView)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bindData(mList[position])
        }

        override fun getItemCount(): Int = mList.count()

        class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item: TextView = itemView.findViewById(R.id.check_goal_item_text)
            fun bindData(it: String) {
                item.text = it
            }
        }
    }

    private fun goalItemSelect(context: Context) = RecyclerItemClickListener(
        context, goalList, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val item : TextView = view as TextView
                goalTextView.text = item.text
                goalButton.apply {
                    rotation = 270f
                    animate()
                        .rotation(180f)
                        .setDuration(shortAnimationDuration.toLong())
                        .setListener(null)
                }
                listShow(goalList, 0)
            }

            override fun onItemLongClick(view: View?, position: Int) {
            }
        }
    )

    class LocationListAdapter(list: MutableList<String>) :
        RecyclerView.Adapter<LocationListAdapter.Holder>() {
        private var mList: MutableSet<String> = list.toMutableSet()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
            val itemView =
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_check_location, parent, false)
            return Holder(itemView)
        }

        override fun onBindViewHolder(holder: Holder, position: Int) {
            holder.bindData(mList.elementAt(position))
        }

        override fun getItemCount(): Int = mList.count()

        class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val item: Button = itemView.findViewById(R.id.check_location_label)
            fun bindData(it: String) {
                item.text = it
            }
        }
    }

    private fun locateItemSelect(context: Context) = RecyclerItemClickListener(
        context, locateList, object : RecyclerItemClickListener.OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val item : Button = view as Button
                locateTextView.text = item.text
            }

            override fun onItemLongClick(view: View?, position: Int) {
            }
        }
    )
}