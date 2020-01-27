package com.droidknights.app2020.ui.schedule

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.droidknights.app2020.base.BaseViewModel
import com.droidknights.app2020.base.DispatcherProvider
import com.droidknights.app2020.db.SessionRepository
import com.droidknights.app2020.ui.data.SessionData
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

/**
 * Created by jiyoung on 04/12/2019
 */
class ScheduleViewModel @Inject constructor(private val dispatchers: DispatcherProvider, repo: SessionRepository) : BaseViewModel() {

    val sessionListData : LiveData<List<SessionData>> = liveData {
        repo.get().collect { emit(it) }
    }

    private val _itemEvent = MutableLiveData<String>()
    val itemEvent : LiveData<String> get() = _itemEvent

    fun onClickItem(sessionId: String) {
        _itemEvent.value = sessionId
    }
}