package com.droidknights.app2020.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidknights.app2020.common.Event
import javax.inject.Inject

/**
 * Created by jiyoung on 29/11/2019
 */
class HomeViewModel @Inject constructor() : ViewModel() {

    val homeText = MutableLiveData<String>().apply { postValue("HomeFragment") }

    private val _navigateToSchedule = MutableLiveData<Event<Unit>>()
    val navigateToSchedule : LiveData<Event<Unit>> get() = _navigateToSchedule

    private val _navigateToInfo = MutableLiveData<Event<Unit>>()
    val navigateToInfo : LiveData<Event<Unit>> get() = _navigateToInfo

    fun onClickSchedule() {
        _navigateToSchedule.value = Event(Unit)
    }

    fun onClickInfo() {
        _navigateToInfo.value = Event(Unit)
    }
}