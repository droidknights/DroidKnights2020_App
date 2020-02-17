package com.droidknights.app2020.ui.sponsor

import androidx.recyclerview.widget.DiffUtil
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.ui.model.UiSponsorModel

/**
 * Created by jiyoung on 03/02/2020
 */
class SponsorAdapter : DataBindingAdapter<UiSponsorModel>(DiffCallback()) {
    override var itemClickListener: ItemClickListener?
        get() = null
        set(value) {}

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