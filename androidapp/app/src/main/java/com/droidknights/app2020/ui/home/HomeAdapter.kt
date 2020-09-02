package com.droidknights.app2020.ui.home

import androidx.recyclerview.widget.DiffUtil
import com.droidknights.app2020.R
import com.droidknights.app2020.common.DataBindingAdapter
import com.droidknights.app2020.data.EventHistory
import com.droidknights.app2020.data.Sponsor
import com.droidknights.app2020.ui.model.UiHomeModel

class HomeAdapter(
    viewModel: HomeViewModel,
    items: List<UiHomeModel>
) : DataBindingAdapter<HomeItemModel>(DiffCallback(), viewModel) {

    init {
        submitList(
            items.map {
                when (it) {
                    is UiHomeModel.Header -> HomeItemModel.Header(it.sponsor)
                    is UiHomeModel.History -> HomeItemModel.Item(it.history)
                }
            }
        )
    }

    private class DiffCallback : DiffUtil.ItemCallback<HomeItemModel>() {
        override fun areItemsTheSame(oldItem: HomeItemModel, newItem: HomeItemModel): Boolean {
            return when {
                oldItem is HomeItemModel.Header && newItem is HomeItemModel.Header -> oldItem.sponsor == newItem.sponsor
                oldItem is HomeItemModel.Item && newItem is HomeItemModel.Item -> oldItem.history == newItem.history
                else -> false
            }
        }

        override fun areContentsTheSame(oldItem: HomeItemModel, newItem: HomeItemModel): Boolean {
            return when {
                oldItem is HomeItemModel.Header && newItem is HomeItemModel.Header -> oldItem == newItem
                oldItem is HomeItemModel.Item && newItem is HomeItemModel.Item -> oldItem == newItem
                else -> true
            }
        }
    }

    override fun getItemViewType(position: Int) = when (getItem(position)) {
        is HomeItemModel.Header -> R.layout.item_home_header
        is HomeItemModel.Item -> R.layout.item_home_history
    }
}

sealed class HomeItemModel {
    data class Header(val sponsor: List<Sponsor>) : HomeItemModel()
    data class Item(val history: EventHistory) : HomeItemModel()
}