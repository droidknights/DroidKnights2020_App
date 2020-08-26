package com.droidknights.app2020.data

import com.droidknights.app2020.R

data class Tag(
    val name: String = "",
    var isSelected: Boolean = false
)

enum class TagEnum(val title: String, val color: Int) {
    ARCHITECTURE("Architecture", R.color.color_sessionChipText_blue),
    FRAMEWORK("Framework", R.color.color_sessionChipText_orange),
    KOTLIN("Kotlin", R.color.color_sessionChipText_purple),
    LANGUAGE("Language", R.color.color_sessionChipText_red),
    UI("UI", R.color.color_sessionChipText_white),
    PRODUCT("생산성", R.color.color_sessionChipText),
    CROSS_PLATFORM("크로스플랫폼", R.color.color_sessionChipText_green),
    FLUTTER("Flutter", R.color.color_sessionChipText_brown);

    companion object {
        fun getColor(title: String): Int? =
            when (title) {
                ARCHITECTURE.title -> ARCHITECTURE.color
                FRAMEWORK.title -> FRAMEWORK.color
                KOTLIN.title -> KOTLIN.color
                LANGUAGE.title -> LANGUAGE.color
                UI.title -> UI.color
                PRODUCT.title -> PRODUCT.color
                CROSS_PLATFORM.title -> CROSS_PLATFORM.color
                FLUTTER.title -> FLUTTER.color
                else -> null
            }
    }
}