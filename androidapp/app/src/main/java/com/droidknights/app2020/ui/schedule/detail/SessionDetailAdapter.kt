package com.droidknights.app2020.ui.schedule.detail

import androidx.recyclerview.widget.DiffUtil
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.data.Session
import com.droidknights.app2020.data.Speaker

class SessionDetailAdapter(
    viewModel: SessionDetailViewModel,
    session: Session
) : DataBindingAdapter<UiSessionDetailModel>(DiffCallback(), viewModel) {

    init {
        submitList(buildMergeList(session))
    }

    private fun buildMergeList(session: Session): List<UiSessionDetailModel> {
        val list = mutableListOf<UiSessionDetailModel>()
        list.add(
            UiSessionDetailModel.UiHeader(
                session.id,
                session.title,
                session.tag.orEmpty(),
                session.speaker.orEmpty()
            )
        )
        list.add(
            UiSessionDetailModel.UiContents(
                session.contents.orEmpty(),
                session.speaker.orEmpty()
            )
        )
        return list
    }

    private class DiffCallback : DiffUtil.ItemCallback<UiSessionDetailModel>() {
        override fun areItemsTheSame(oldItem: UiSessionDetailModel, newItem: UiSessionDetailModel): Boolean {
            return when {
                oldItem is UiSessionDetailModel.UiHeader && newItem is UiSessionDetailModel.UiHeader -> oldItem.id == newItem.id
                oldItem is UiSessionDetailModel.UiContents && newItem is UiSessionDetailModel.UiContents -> oldItem.contents == newItem.contents
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: UiSessionDetailModel, newItem: UiSessionDetailModel): Boolean {
            return when {
                oldItem is UiSessionDetailModel.UiHeader && newItem is UiSessionDetailModel.UiHeader -> oldItem == newItem
                oldItem is UiSessionDetailModel.UiContents && newItem is UiSessionDetailModel.UiContents -> oldItem == newItem
                else -> true
            }
        }
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is UiSessionDetailModel.UiHeader -> R.layout.item_session_detail_header
        is UiSessionDetailModel.UiContents -> R.layout.item_session_detail_contents
    }
}

sealed class UiSessionDetailModel {
    data class UiHeader(
        val id: String,
        val title: String,
        val tags: List<String> = emptyList(),
        val speakers: List<Speaker> = emptyList()
    ) : UiSessionDetailModel()
    data class UiContents(val contents: String, val speakers: List<Speaker>) : UiSessionDetailModel()
}