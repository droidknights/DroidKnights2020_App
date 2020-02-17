package com.droidknights.app2020.mapper

import com.droidknights.app2020.data.Session
import com.droidknights.app2020.data.Sponsor
import com.droidknights.app2020.ui.model.UiSessionModel
import com.droidknights.app2020.ui.model.UiSponsorModel


fun Session.asUiModel() =
    UiSessionModel(
        id = id,
        title = title,
        time = time
    )

fun Sponsor.asUiModel() =
    UiSponsorModel(
        name = name,
        url = url,
        imageDrawableResId = image ?: 0
    )
