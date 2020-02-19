package com.droidknights.app2020.ui.schedule.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.droidknights.app2020.base.BaseViewModel
import com.droidknights.app2020.base.DispatcherProvider
import com.droidknights.app2020.db.SessionRepository
import com.droidknights.app2020.data.Session
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionDetailViewModel @Inject constructor(
    dispatchers: DispatcherProvider,
    private val repo: SessionRepository
) : BaseViewModel() {

    val sessionContents = MutableLiveData<Session>()

    fun getSessionFromFirestore(id: String) {
        viewModelScope.launch {
            repo.getById(id).collect {
                sessionContents.value = it
            }
        }
    }
}
