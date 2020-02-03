package com.droidknights.app2020.ui.sponsor

import androidx.recyclerview.widget.DiffUtil
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.ui.data.SponsorData

/**
 * Created by jiyoung on 03/02/2020
 */
class SponsorAdapter : DataBindingAdapter<SponsorData>(DiffCallback()) {
    override var itemClickListener: ItemClickListener?
        get() = null
        set(value) {}

    class DiffCallback : DiffUtil.ItemCallback<SponsorData>() {
        override fun areItemsTheSame(oldItem: SponsorData, newItem: SponsorData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: SponsorData, newItem: SponsorData): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_sponsor

}