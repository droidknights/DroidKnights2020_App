package com.droidknights.app2020.ui.schedule.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.droidknights.app2020.R
import com.droidknights.app2020.base.BaseViewModel
import com.droidknights.app2020.base.DispatcherProvider
import com.droidknights.app2020.common.Event
import com.droidknights.app2020.data.Session
import com.droidknights.app2020.db.SessionRepository
import kotlinx.coroutines.flow.flowOn

class SessionDetailViewModel @ViewModelInject constructor(
    private val dispatchers: DispatcherProvider,
    private val repo: SessionRepository
) : BaseViewModel() {

    private val _id: MutableLiveData<String> = MutableLiveData()

    private val _videoEvent = MutableLiveData<Event<String>>()
    val videoEvent: LiveData<Event<String>> = _videoEvent

    private val _qnaEvent = MutableLiveData<Event<String>>()
    val qnaEvent: LiveData<Event<String>> = _qnaEvent

    private val _toastEvent = MutableLiveData<Event<Int>>()
    val toastEvent: LiveData<Event<Int>> = _toastEvent

    private val _shareEvent = MutableLiveData<Event<String>>()
    val shareEvent: LiveData<Event<String>> = _shareEvent

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

    fun onClickVideoLink() {
        _videoEvent.value = Event(getVideoLink())
    }

    fun onClickQnALink() {
        val qnaLink = sessionContents.value?.qnaLink
        if (qnaLink.isNullOrEmpty()) {
            _toastEvent.value = Event(R.string.session_detail_qna_link_tbd)
            return
        }

        _qnaEvent.value = Event(qnaLink)
    }

    fun onClickShare() {
        _shareEvent.value = Event(getVideoLink())
    }

    private fun getVideoLink(): String {
        val videoLink = sessionContents.value?.videoLink
        return if (videoLink.isNullOrEmpty()) DEFAULT_VIDEO_LINK else videoLink
    }

    companion object {
        private const val DEFAULT_VIDEO_LINK = "https://www.youtube.com/channel/UCjeUnwS8mHhsl600-nFJKmw"
    }
}
