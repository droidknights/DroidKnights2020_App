package com.droidknights.app2020.data

data class Session(
    var id: String = "",
    var track: Int = 0,
    var title: String = "",
    var tag: List<String>? = emptyList(),
    var time: String = "",
    var contents: String? = "",
    var speakerName: String? = "",
    var speakerDesc: String? = "",
    var speakerProfile: String? = ""
)