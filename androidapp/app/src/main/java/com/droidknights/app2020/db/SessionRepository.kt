package com.droidknights.app2020.db

import com.droidknights.app2020.data.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun get(isCacheFirstLoad: Boolean = true): Flow<List<Session>>
    suspend fun getById(id: String): Flow<Session>
}