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

/**
 * 1차: time 순서
 * 2차: track 순서
 */
fun List<Session>.toSortedSessions(): List<Session> =
    sortedWith(
        compareBy(
            { it.time },
            { it.track }
        )
    )