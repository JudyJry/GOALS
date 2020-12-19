package com.example.test.ui.act

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.ActItem

class ActViewModel : ViewModel() {
    private val itemPos: Int = ActItem.getItemPos()
    private val item = ActItem.item[itemPos]
    private val img: Int = item["image"] as Int
    private val title: String = item["title"] as String
    private val time: String = item["time"] as String
    private val location: String = item["location"] as String
    private val cost: String = item["cost"] as String
    private val count: String = item["count"] as String
    private val joined : Boolean = ActItem.joined[itemPos]

    private val _img = MutableLiveData<Int>().apply { value = img }
    private val _title = MutableLiveData<String>().apply { value = title }
    private val _time = MutableLiveData<String>().apply { value = time }
    private val _location = MutableLiveData<String>().apply { value = location }
    private val _cost = MutableLiveData<String>().apply { value = cost }
    private val _count = MutableLiveData<String>().apply { value = count }
    private val _joined = MutableLiveData<Boolean>().apply { value = joined }

    val mImg: LiveData<Int> = _img
    val mTitle: LiveData<String> = _title
    val mTime: LiveData<String> = _time
    val mLocation: LiveData<String> = _location
    val mCost: LiveData<String> = _cost
    val mCount: LiveData<String> = _count
    val mJoined : LiveData<Boolean> = _joined
}
