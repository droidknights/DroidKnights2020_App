package com.droidknights.app2020.ui.info

import androidx.recyclerview.widget.DiffUtil
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.common.DataBindingViewHolder
import com.droidknights.app2020.ui.model.UiSponsorModel

/**
 * Created by jiyoung on 03/02/2020
 */
class SponsorAdapter : DataBindingAdapter<UiSponsorModel>(DiffCallback()) {
    override var itemClickListener: ItemClickListener? = null

    class DiffCallback : DiffUtil.ItemCallback<UiSponsorModel>() {
        override fun areItemsTheSame(oldItem: UiSponsorModel, newItem: UiSponsorModel): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: UiSponsorModel, newItem: UiSponsorModel): Boolean {
            return oldItem.name == newItem.name
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_sponsor

    override fun onBindViewHolder(holder: DataBindingViewHolder<UiSponsorModel>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener {
            val url = getItem(position).url
            itemClickListener?.onClickItem(url)
        }
    }
}