package com.droidknights.app2020.binding

import android.webkit.WebView
import android.widget.ImageView
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.droidknights.app2020.ui.home.HomeAdapter
import com.droidknights.app2020.ui.home.HomeViewModel
import com.droidknights.app2020.ui.model.UiHomeModel

@BindingAdapter(value = ["bindImgRes"])
fun ImageView.bindSetImage(resId: Int) = setImageResource(resId)

@BindingAdapter(value = ["isRefreshing"])
fun SwipeRefreshLayout.bindRefreshing(isRefreshing: Boolean) {
    this.isRefreshing = isRefreshing
}

@BindingAdapter(value = ["onRefresh"])
fun SwipeRefreshLayout.bindRefreshListener(onRefreshListener: SwipeRefreshLayout.OnRefreshListener) =
    setOnRefreshListener(onRefreshListener)

@BindingAdapter("webUrl")
fun WebView.bindUrl(value: String?) = value?.let(::loadUrl)

@BindingAdapter("homeVm", "homeItems")
fun RecyclerView.bindHome(vm: HomeViewModel, items: List<UiHomeModel>?) {
    if (items?.isNotEmpty() == true) {
        isVisible = true
        adapter = HomeAdapter(vm, items)
    } else {
        isGone = true
    }
}