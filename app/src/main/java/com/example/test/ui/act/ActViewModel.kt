package com.example.test.ui.act

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.ActItem

class ActViewModel : ViewModel() {

    private val img: MutableList<Int> = ActItem.List
    private val title: MutableList<Int> = ActItem.Title
    private val time: MutableList<Int> = ActItem.Time
    private val location: MutableList<Int> = ActItem.Location
    private val cost: MutableList<Int> = ActItem.Cost
    private val count: MutableList<Int> = ActItem.Count
    private val joined : MutableList<Boolean> = ActItem.joined

    private val _img = MutableLiveData<MutableList<Int>>().apply { value = img }
    private val _title = MutableLiveData<MutableList<Int>>().apply { value = title }
    private val _time = MutableLiveData<MutableList<Int>>().apply { value = time }
    private val _location = MutableLiveData<MutableList<Int>>().apply { value = location }
    private val _cost = MutableLiveData<MutableList<Int>>().apply { value = cost }
    private val _count = MutableLiveData<MutableList<Int>>().apply { value = count }
    private val _joined = MutableLiveData<MutableList<Boolean>>().apply { value = joined }

    val mImg: LiveData<MutableList<Int>> = _img
    val mTitle: LiveData<MutableList<Int>> = _title
    val mTime: LiveData<MutableList<Int>> = _time
    val mLocation: LiveData<MutableList<Int>> = _location
    val mCost: LiveData<MutableList<Int>> = _cost
    val mCount: LiveData<MutableList<Int>> = _count
    val mJoined : LiveData<MutableList<Boolean>> = _joined
}
