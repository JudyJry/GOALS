package com.example.test

import com.example.test.R

object ActItem : ArrayList<Int>() {

    val List: MutableList<Int> = arrayListOf(
        R.drawable.banner_image1,
        R.drawable.banner_image2,
        R.drawable.banner_image3
    )
    val Title: MutableList<Int> = arrayListOf(
        R.string.Act_Item_Title_1,
        R.string.Act_Item_Title_2,
        R.string.Act_Item_Title_3
    )
    val Cost: MutableList<Int> = arrayListOf(
        R.string.Act_Item_Cost_1,
        R.string.Act_Item_Cost_2,
        R.string.Act_Item_Cost_3
    )
    val Location: MutableList<Int> = arrayListOf(
        R.string.Act_Item_Location_1,
        R.string.Act_Item_Location_2,
        R.string.Act_Item_Location_3
    )
    val Time: MutableList<Int> = arrayListOf(
        R.string.Act_Item_Time_1,
        R.string.Act_Item_Time_2,
        R.string.Act_Item_Time_3
    )
    val Count: MutableList<Int> = arrayListOf(
        R.string.Act_Item_Count_1,
        R.string.Act_Item_Count_2,
        R.string.Act_Item_Count_3
    )

    val joined : MutableList<Boolean> = arrayListOf(
        false,
        false,
        false
    )

}
