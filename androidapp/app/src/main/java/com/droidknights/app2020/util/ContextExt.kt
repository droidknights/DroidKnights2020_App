package com.droidknights.app2020.util

import android.content.res.Resources

fun Int.dp2px() = (this * Resources.getSystem().displayMetrics.density).toInt()