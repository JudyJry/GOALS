package com.example.test.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.test.R


class HomeViewModel : ViewModel() {

    private val title1:Int= R.string.Main_Title_1

    private val _text = MutableLiveData<Int>().apply {
        value = title1
    }
    val text: LiveData<Int> = _text
}