package com.droidknights.app2020.ui.schedule

import androidx.recyclerview.widget.DiffUtil
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.ui.model.UiSessionModel

class ScheduleAdapter(viewModel: ScheduleViewModel) : DataBindingAdapter<UiSessionModel>(DiffCallback(), viewModel) {

    class DiffCallback : DiffUtil.ItemCallback<UiSessionModel>() {
        override fun areItemsTheSame(oldItem: UiSessionModel, newItem: UiSessionModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UiSessionModel, newItem: UiSessionModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_session

}