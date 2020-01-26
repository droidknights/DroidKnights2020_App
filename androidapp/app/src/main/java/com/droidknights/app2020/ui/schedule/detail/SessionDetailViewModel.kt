package com.droidknights.app2020.ui.schedule.detail

import androidx.lifecycle.*
import com.droidknights.app2020.base.BaseViewModel
import com.droidknights.app2020.base.DispatcherProvider
import com.droidknights.app2020.db.SessionRepository
import com.droidknights.app2020.ui.data.SessionData
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.single
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionDetailViewModel @Inject constructor(dispatchers: DispatcherProvider, val repo: SessionRepository) : BaseViewModel() {

    val sessionContents  = MutableLiveData<SessionData>()

    fun getSessionFromFirestore(id: String) {
        viewModelScope.launch {
            repo.getById(id).collect {
                sessionContents.value = it
            }
        }
    }
}
