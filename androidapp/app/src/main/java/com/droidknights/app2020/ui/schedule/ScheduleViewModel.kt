package com.droidknights.app2020.ui.schedule

import androidx.lifecycle.*
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

    private val _refreshEvent = MutableLiveData<Unit>()
    val sessionListData: LiveData<List<SessionData>> = _refreshEvent.switchMap {
        liveData {
            repo.get().collect { emit(it) }
        }
    }
    val isRefreshing: LiveData<Boolean> = sessionListData.map { false }

    private val _itemEvent = MutableLiveData<String>()
    val itemEvent: LiveData<String> get() = _itemEvent

    init {
        refresh()
    }

    fun refresh() {
        _refreshEvent.value = Unit
    }

    fun onClickItem(sessionId: String) {
        _itemEvent.value = sessionId
    }
}