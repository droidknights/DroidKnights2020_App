package com.droidknights.app2020.common

import android.content.Context
import com.google.gson.Gson
import timber.log.Timber


inline fun <reified ENTITY> Context.loadJson(gson: Gson, fileName: String): ENTITY {
    val result = assets.open(fileName)
        .bufferedReader()
        .use { it.readText() }

    return gson.fromJson(result, ENTITY::class.java)
}