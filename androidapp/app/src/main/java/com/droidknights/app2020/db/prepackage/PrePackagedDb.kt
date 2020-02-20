package com.droidknights.app2020.db.prepackage

import com.droidknights.app2020.data.Session

interface PrePackagedDb {
    suspend fun getSessionList(): List<Session>
    suspend fun getSessionById(id: String): Session?
}
