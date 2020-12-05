package com.example.test.ui.goal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.GoalItem

class GoalViewModel(itemPos: Int) : ViewModel() {
    val g = GoalItem.g[itemPos]
    private val title: String? = g["title"]
    private val time: String? = g["time"]
    private val location: String? = g["location"]
    private val joined: Boolean = GoalItem.joined[itemPos]

    private val _title = MutableLiveData<String>().apply { value = title }
    private val _time = MutableLiveData<String>().apply { value = time }
    private val _location = MutableLiveData<String>().apply { value = location }
    private val _joined = MutableLiveData<Boolean>().apply { value = joined }

    val mTitle: LiveData<String> = _title
    val mTime: LiveData<String> = _time
    val mLocation: LiveData<String> = _location
    val mJoined: LiveData<Boolean> = _joined
}
