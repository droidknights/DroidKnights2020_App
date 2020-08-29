package com.droidknights.app2020.db.prepackage

import android.content.Context
import com.droidknights.app2020.data.Session
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber

class PrePackagedDbImpl(
    val context: Context,
    val gson: Gson,
    val assetsName: String
) : PrePackagedDb {

    private val sessionList: PrePackagedSessionList = loadJson(assetsName)

    override suspend fun getSessionList(): List<Session> {
        return sessionList.session
    }

    override suspend fun getSessionById(id: String): Session? {
        return sessionList.session.find { it.id == id }
    }

    private class PrePackagedSessionList(val session: List<Session>)

    inline fun <reified ENTITY> loadJson(fileName: String): ENTITY {
        val result = try {
            context.assets.open(fileName)
                .bufferedReader()
                .use { it.readText() }

        } catch (e: Exception) {
            Timber.e(e)
            null
        }

        val collectionType = object : TypeToken<ENTITY>() {}.type
        return gson.fromJson(result, collectionType) as ENTITY
    }
}