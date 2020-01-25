package com.droidknights.app2020.db

import com.droidknights.app2020.ui.data.SessionData
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun get(): Flow<List<SessionData>>
    fun getById(id: String): Flow<SessionData>
}