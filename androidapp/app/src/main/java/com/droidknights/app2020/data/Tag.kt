package com.droidknights.app2020.data

import com.droidknights.app2020.R

data class Tag(
    val name: String = "",
    var isSelected: Boolean = false,
    var color : Int? = getColor(name)
)

fun getColor(title: String): Int? =
    when (title) {
        "Architecture" -> R.color.color_sessionChipText_blue
        "Framework" -> R.color.color_sessionChipText_orange
        "Kotlin" -> R.color.color_sessionChipText_purple
        "Language" -> R.color.color_sessionChipText_red
        "UI" -> R.color.color_sessionChipText_white
        "생산성" -> R.color.color_sessionChipText
        "크로스플랫폼" -> R.color.color_sessionChipText_green
        "Flutter" -> R.color.color_sessionChipText_brown
        else -> null
    }