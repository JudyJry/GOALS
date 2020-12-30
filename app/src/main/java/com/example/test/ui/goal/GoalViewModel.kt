package com.example.test.ui.goal

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.GoalItem

class GoalViewModel : ViewModel() {
    private val itemPos: Int = GoalItem.getItemPos()
    val g = GoalItem.item[itemPos]
    private val title: String = g["title"] as String
    private val time: String = g["time"] as String
    private val author: String = g["author"] as String
    private val member: String = g["member"].toString()
    private val maxMember: String = g["maxMember"].toString()
    private val context: String = g["context"] as String
    private val location: String = g["location"] as String
    private val joined: Boolean = GoalItem.joined[itemPos]
    private val user : Int = g["user"] as Int
    private val img : Int = g["img"] as Int

    private val _title = MutableLiveData<String>().apply { value = title }
    private val _time = MutableLiveData<String>().apply { value = time }
    private val _author = MutableLiveData<String>().apply { value = "發起人：$author" }
    private val _member = MutableLiveData<String>().apply { value = "$member/$maxMember" }
    private val _context = MutableLiveData<String>().apply { value = context }
    private val _location = MutableLiveData<String>().apply { value = location }
    private val _joined = MutableLiveData<Boolean>().apply { value = joined }
    private val _user = MutableLiveData<Int>().apply { value = user }
    private val _img = MutableLiveData<Int>().apply { value = img }

    val mTitle: LiveData<String> = _title
    val mTime: LiveData<String> = _time
    val mAuthor: LiveData<String> = _author
    val mMember: LiveData<String> = _member
    val mContext: LiveData<String> = _context
    val mLocation: LiveData<String> = _location
    val mJoined: LiveData<Boolean> = _joined
    val mUser: LiveData<Int> = _user
    val mImg: LiveData<Int> = _img
}
