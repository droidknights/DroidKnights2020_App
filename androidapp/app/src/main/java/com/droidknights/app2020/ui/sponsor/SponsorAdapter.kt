package com.droidknights.app2020.ui.sponsor

import androidx.recyclerview.widget.DiffUtil
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.common.DataBindingViewHolder
import com.droidknights.app2020.ui.model.UiSponsorModel
import com.droidknights.app2020.ui.schedule.ScheduleViewModel

/**
 * Created by jiyoung on 03/02/2020
 */
class SponsorAdapter(viewModel: SponsorViewModel) : DataBindingAdapter<UiSponsorModel>(DiffCallback(), viewModel) {

    class DiffCallback : DiffUtil.ItemCallback<UiSponsorModel>() {
        override fun areItemsTheSame(oldItem: UiSponsorModel, newItem: UiSponsorModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: UiSponsorModel, newItem: UiSponsorModel): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_sponsor

}