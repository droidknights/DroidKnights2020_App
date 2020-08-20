package com.droidknights.app2020.ui.schedule.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.droidknights.app2020.base.BaseViewModel
import com.droidknights.app2020.base.DispatcherProvider
import com.droidknights.app2020.data.Session
import com.droidknights.app2020.db.SessionRepository
import kotlinx.coroutines.flow.flowOn

class SessionDetailViewModel @ViewModelInject constructor(
    private val dispatchers: DispatcherProvider,
    private val repo: SessionRepository
) : BaseViewModel() {

    private val _id: MutableLiveData<String> = MutableLiveData()

    val sessionContents: LiveData<Session> = _id.switchMap { id ->
        liveData {
            emitSource(
                repo.getById(id).flowOn(dispatchers.default()).asLiveData()
            )
        }
    }

    fun getSession(id: String) {
        _id.value = id
    }
}
