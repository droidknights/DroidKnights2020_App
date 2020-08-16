package com.droidknights.app2020.ui.model

import com.droidknights.app2020.data.Session
import com.droidknights.app2020.data.Sponsor
import com.droidknights.app2020.data.Tag


fun Session.asUiModel() =
    UiSessionModel(
        id = id,
        title = title,
        time = time,
        tag = tag?.map { Tag(it, true) }
    )

fun Sponsor.asUiModel() =
    UiSponsorModel(
        name = name,
        url = url,
        image = image ?: 0
    )