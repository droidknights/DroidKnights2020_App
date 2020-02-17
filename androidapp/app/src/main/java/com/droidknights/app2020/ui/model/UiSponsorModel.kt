package com.droidknights.app2020.ui.model

import androidx.annotation.DrawableRes


data class UiSponsorModel(
    val name: String,
    val url: String,
    @DrawableRes val imageDrawableResId: Int
)