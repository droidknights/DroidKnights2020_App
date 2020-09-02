package com.droidknights.app2020.util

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.clearItemDecoration() {
    repeat(itemDecorationCount) {
        removeItemDecorationAt(0)
    }
}