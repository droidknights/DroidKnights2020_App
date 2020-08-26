package com.droidknights.app2020.binding

import android.webkit.WebView
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.droidknights.app2020.R
import com.droidknights.app2020.data.Tag
import com.droidknights.app2020.ui.schedule.TagAdapter
import com.droidknights.app2020.widget.SessionChip

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

@BindingAdapter("setColor")
fun SessionChip.setColor(color: Int) {
    this.chipTextColor = color
    this.strokeColor = color
    this.chipBackgroundColor = when (color) {
        ContextCompat.getColor(context, R.color.toolbar_textColor_black) -> ContextCompat.getColor(context, R.color.color_sessionChipText_white)
        ContextCompat.getColor(context, R.color.color_sessionChipText_white) -> ContextCompat.getColor(context, R.color.toolbar_textColor_black)
        else -> lighten(color, 30)
    }
}

@BindingAdapter("setData")
fun RecyclerView.setData(items: List<Tag>?) {
    this.adapter = TagAdapter()
    (this.adapter as TagAdapter).run {
        submitList(items)
    }
}