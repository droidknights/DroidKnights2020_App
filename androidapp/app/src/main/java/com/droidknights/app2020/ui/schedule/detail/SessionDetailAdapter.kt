package com.droidknights.app2020.ui.schedule.detail

import androidx.recyclerview.widget.DiffUtil
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.data.Session
import com.droidknights.app2020.data.Speaker

class SessionDetailAdapter(
    viewModel: SessionDetailViewModel,
    session: Session
) : DataBindingAdapter<UiSpeakerModel>(DiffCallback(), viewModel) {

    init {
        submitList(buildMergeList(session))
    }

    private fun buildMergeList(session: Session) = listOf(
        // TODO: 회사 소속
        UiSpeakerModel.UiSpeaker(session.speaker.orEmpty(), ""),
        UiSpeakerModel.UiSession(session.tag.orEmpty(), session.contents.orEmpty())
    )

    private class DiffCallback : DiffUtil.ItemCallback<UiSpeakerModel>() {
        override fun areItemsTheSame(oldItem: UiSpeakerModel, newItem: UiSpeakerModel): Boolean {
            return when {
                oldItem is UiSpeakerModel.UiSpeaker && newItem is UiSpeakerModel.UiSpeaker -> oldItem == newItem
                oldItem is UiSpeakerModel.UiSession && newItem is UiSpeakerModel.UiSession -> oldItem == newItem
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: UiSpeakerModel, newItem: UiSpeakerModel): Boolean {
            return when {
                oldItem is UiSpeakerModel.UiSpeaker && newItem is UiSpeakerModel.UiSpeaker -> oldItem == newItem
                oldItem is UiSpeakerModel.UiSession && newItem is UiSpeakerModel.UiSession -> oldItem == newItem
                else -> true
            }
        }
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is UiSpeakerModel.UiSpeaker -> R.layout.item_session_detail_speaker
        is UiSpeakerModel.UiSession -> R.layout.item_session_detail_session
    }
}

sealed class UiSpeakerModel {
    data class UiSpeaker(val speakers: List<Speaker>, val company: String) : UiSpeakerModel()
    data class UiSession(val tags: List<String>, val contents: String) : UiSpeakerModel()
}