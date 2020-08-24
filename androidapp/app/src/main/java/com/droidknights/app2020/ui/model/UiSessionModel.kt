package com.droidknights.app2020.ui.model

import com.droidknights.app2020.data.Tag

data class UiSessionModel(
    val id: String,
    val title: String,
    val time: String,
    val tag: List<Tag>?,
    val speakerName: String,
    val isLive:Boolean
)
