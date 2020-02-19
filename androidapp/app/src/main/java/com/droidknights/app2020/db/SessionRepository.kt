package com.droidknights.app2020.db

import com.droidknights.app2020.data.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    fun get(): Flow<List<Session>>
    fun getById(id: String): Flow<Session>
}