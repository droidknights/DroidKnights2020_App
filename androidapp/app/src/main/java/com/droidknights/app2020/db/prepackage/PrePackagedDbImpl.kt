package com.droidknights.app2020.db.prepackage

import android.content.Context
import com.droidknights.app2020.data.Session
import com.google.gson.Gson
import timber.log.Timber

class PrePackagedDbImpl(
    context: Context,
    assetsName: String
) : PrePackagedDb {

    private val sessionList: List<Session> =
        context.readJsonStringFromAsset(assetsName)?.toSessionList().orEmpty()

    private fun String.toSessionList(): List<Session> {
        return Gson().fromJson(this, PrePackagedSessionList::class.java).session
    }

    override suspend fun getSessionList(): List<Session> {
        return sessionList
    }

    override suspend fun getSessionById(id: String): Session? {
        return sessionList.find { it.id == id }
    }

    private fun Context.readJsonStringFromAsset(assetsName: String): String? {
        return try {
            assets.open(assetsName)
                .bufferedReader()
                .use { it.readText() }
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    private class PrePackagedSessionList(val session: List<Session>)
}