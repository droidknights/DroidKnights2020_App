package com.droidknights.app2020.ui.model

import com.droidknights.app2020.data.EventHistory
import com.droidknights.app2020.data.Sponsor

sealed class UiHomeModel {
    data class Header(val sponsor: List<Sponsor>) : UiHomeModel()
    data class History(val history: EventHistory) : UiHomeModel()
}