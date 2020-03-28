package com.droidknights.app2020.ui.info.sponsor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class SponsorViewModel @Inject constructor() : ViewModel() {
    private val _webUrl: MutableLiveData<String> = MutableLiveData()

    val trigger = object : Trigger {
        override fun setupUrl(webUrl: String) {
            _webUrl.value = webUrl
        }
    }

    val bundle = object : LiveBundle {
        override val webUrl: LiveData<String> = _webUrl
    }

    interface Trigger {
        fun setupUrl(webUrl: String)
    }

    interface LiveBundle {
        val webUrl: LiveData<String>
    }
}