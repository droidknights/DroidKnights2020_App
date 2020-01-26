package com.droidknights.app2020.ui.data

data class SessionData(
    var id: String = "",
    var track: Int = 0,
    var title: String = "",
    var tag: List<String>? = emptyList(),
    var time: String = "",
    var contents: String? = ""
)