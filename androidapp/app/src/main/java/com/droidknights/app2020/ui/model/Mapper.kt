package com.droidknights.app2020.ui.model

import com.droidknights.app2020.data.Session
import com.droidknights.app2020.data.Sponsor


fun Session.asUiModel() =
    UiSessionModel(
        id = id,
        title = title,
        time = time,
        tag = tag
    )

fun Sponsor.asUiModel() =
    UiSponsorModel(
        name = name,
        url = url,
        image = image ?: 0
    )