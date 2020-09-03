package com.droidknights.app2020.common

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<T>(val binding: ViewDataBinding, private val viewModel: ViewModel) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: T) {
        binding.setVariable(BR.item, item)
        binding.setVariable(BR.viewModel, viewModel)
        binding.executePendingBindings()
    }
}