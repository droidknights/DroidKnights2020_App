package com.droidknights.app2020.ui.settings

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidknights.app2020.common.Event

class SettingsViewModel @ViewModelInject constructor() : ViewModel() {
    // TODO: Implement the ViewModel

    private val _osslEvent = MutableLiveData<Event<String>>()
    val osslEvent: LiveData<Event<String>> get() = _osslEvent

    fun onClickOssl() {
        _osslEvent.value = Event("")
    }
}
