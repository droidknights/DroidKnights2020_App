package com.droidknights.app2020.binding

import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.droidknights.app2020.R
import com.droidknights.app2020.data.Speaker
import com.droidknights.app2020.data.Tag
import com.droidknights.app2020.ui.schedule.TagAdapter
import com.droidknights.app2020.ui.schedule.detail.DetailTagAdapter
import com.droidknights.app2020.widget.SessionChip
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager

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
fun SessionChip.setColor(@ColorInt color: Int) {
    this.chipTextColor = color
    this.strokeColor = color
    this.chipBackgroundColor = when (color) {
        ContextCompat.getColor(context, R.color.color_sessionChipText) -> ContextCompat.getColor(context, R.color.color_sessionChipText_white)
        ContextCompat.getColor(context, R.color.color_sessionChipText_white) -> ContextCompat.getColor(context, R.color.color_sessionChipText)
        else -> lighten(color, 30)
    }
}

@BindingAdapter("setData")
fun RecyclerView.setData(items: List<Tag>?) {
    FlexboxLayoutManager(context).apply {
        flexWrap = FlexWrap.WRAP
        flexDirection = FlexDirection.ROW
    }.let {
        this.layoutManager = it
    }
    this.adapter = TagAdapter()
    (this.adapter as TagAdapter).run {
        submitList(items)
    }
}

@BindingAdapter("speakerName")
fun TextView.bindSpeakerName(value: Speaker?) {
    text = value?.name
}

@BindingAdapter("speakerImage")
fun ImageView.bindProfile(speaker: Speaker?) {
    Glide.with(this)
        .load(speaker?.profileImage)
        .apply(
            RequestOptions()
                .placeholder(R.drawable.img_droid_space)
                .circleCrop()
        ).into(this)
}

@BindingAdapter("sessionTags")
fun RecyclerView.bindSessionTags(tags: List<String>?) {
    if (tags?.isNotEmpty() == true) {
        isVisible = true
        adapter = (adapter as? DetailTagAdapter ?: DetailTagAdapter()).apply {
            this.tags = tags
        }
    } else {
        isGone = true
    }
}
