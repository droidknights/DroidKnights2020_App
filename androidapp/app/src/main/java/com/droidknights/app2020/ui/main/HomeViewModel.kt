package com.droidknights.app2020.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * Created by jiyoung on 29/11/2019
 */
class HomeViewModel @Inject constructor() : ViewModel() {

    val homeText = MutableLiveData<String>().apply { postValue("HomeFragment") }
}