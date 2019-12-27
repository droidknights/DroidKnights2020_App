package com.droidknights.app2020.db

import androidx.lifecycle.LiveData
import com.droidknights.app2020.ui.data.SessionData

interface SessionRepository {
    fun get(): LiveData<List<SessionData>>
    fun getById(id: String): LiveData<SessionData>
}