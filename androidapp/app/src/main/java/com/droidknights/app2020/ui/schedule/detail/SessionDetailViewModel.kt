package com.droidknights.app2020.ui.schedule.detail

import androidx.lifecycle.*
import com.droidknights.app2020.base.BaseViewModel
import com.droidknights.app2020.base.DispatcherProvider
import com.droidknights.app2020.data.Session
import com.droidknights.app2020.db.SessionRepository
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SessionDetailViewModel @Inject constructor(
    dispatchers: DispatcherProvider,
    private val repo: SessionRepository
) : BaseViewModel() {

    private val _id: MutableLiveData<String> = MutableLiveData()

    val sessionContents: LiveData<Session> = _id.switchMap { id ->
        liveData<Session> {
            emitSource(
                repo.getById(id).flowOn(dispatchers.default()).asLiveData()
            )
        }
    }

    fun getSession(id: String) {
        _id.value = id
    }
}
