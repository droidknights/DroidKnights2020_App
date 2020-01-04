package com.droidknights.app2020.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.droidknights.app2020.common.Event
import com.droidknights.app2020.db.SessionRepository
import com.droidknights.app2020.ui.data.SessionData
import javax.inject.Inject

/**
 * Created by jiyoung on 04/12/2019
 */
class ScheduleViewModel @Inject constructor(repo: SessionRepository) : ViewModel() {

    private val _sessionListData = repo.get()
    val sessionListData : LiveData<List<SessionData>> get() = _sessionListData
}