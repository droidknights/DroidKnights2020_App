package com.droidknights.app2020.data

data class Session(
    val id: String = "",
    val track: Int = 0,
    val title: String = "",
    val tag: List<String>? = emptyList(),
    val time: String = "",
    val contents: String? = "",
    val speakerName: String? = "",
    val videoLink: String? = "",
    val qnaLink: String? = "",
    val speaker: List<Speaker>? = null,
    var isLive:Boolean = false
)