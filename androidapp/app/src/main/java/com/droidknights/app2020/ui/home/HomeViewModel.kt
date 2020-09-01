package com.droidknights.app2020.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.droidknights.app2020.R
import com.droidknights.app2020.data.EventHistory
import com.droidknights.app2020.data.Sponsor
import com.droidknights.app2020.ui.model.UiHomeModel

class HomeViewModel @ViewModelInject constructor() : ViewModel() {
    private val sponsors = listOf(
        Sponsor("toss", "https://toss.im/", R.drawable.ic_sponsor_toss),
        Sponsor("헤이딜러", "https://dealer.heydealer.com/", R.drawable.ic_sponsor_heydealer),
        Sponsor("카카오페이", "https://www.kakaopay.com/", R.drawable.ic_sponsor_kakaopay),
        Sponsor("vcnc", "https://tadacareer.vcnc.co.kr/", R.drawable.ic_sponsor_vcnc)
    )

    private val eventHistory = listOf(
        EventHistory(2020, "https://droidknights.github.io/2020/"),
        EventHistory(2019, "https://droidknights.github.io/2019/"),
        EventHistory(2018, "https://droidknights.github.io/2018/"),
        EventHistory(2017, "https://droidknights.github.io/2017/"),
    )

    private val _homeItems: MutableLiveData<List<UiHomeModel>> = MutableLiveData()
    val homeItems: LiveData<List<UiHomeModel>> get() = _homeItems

    init {
        val list = mutableListOf<UiHomeModel>()
        list.add(UiHomeModel.Header(sponsors))
        list.addAll(
            eventHistory.map {
                UiHomeModel.History(it)
            }
        )
        _homeItems.value = list
    }
}