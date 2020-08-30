package com.droidknights.app2020.db.prepackage

import android.content.Context
import com.droidknights.app2020.common.loadJson
import com.droidknights.app2020.data.Session
import com.google.gson.Gson
import timber.log.Timber

class PrePackagedDbImpl(
    context: Context,
    gson: Gson,
    assetsName: String
) : PrePackagedDb {

    private val sessionList: PrePackagedSessionList = try {
        context.loadJson(gson, assetsName)
    } catch (e: Exception) {
        Timber.e("Can not read Json file : $e")
        PrePackagedSessionList(emptyList())
    }

    override suspend fun getSessionList(): List<Session> {
        return sessionList.session
    }

    override suspend fun getSessionById(id: String): Session? {
        return sessionList.session.find { it.id == id }
    }

    private class PrePackagedSessionList(val session: List<Session>)

}