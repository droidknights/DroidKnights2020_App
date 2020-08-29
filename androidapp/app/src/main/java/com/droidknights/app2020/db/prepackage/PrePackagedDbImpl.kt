package com.droidknights.app2020.db.prepackage

import android.content.Context
import com.droidknights.app2020.common.loadJson
import com.droidknights.app2020.data.Session
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber

class PrePackagedDbImpl(
    context: Context,
    gson: Gson,
    assetsName: String
) : PrePackagedDb {

    private val sessionList: PrePackagedSessionList = context.loadJson(gson, assetsName)

    override suspend fun getSessionList(): List<Session> {
        return sessionList.session
    }

    override suspend fun getSessionById(id: String): Session? {
        return sessionList.session.find { it.id == id }
    }

    private class PrePackagedSessionList(val session: List<Session>)

}