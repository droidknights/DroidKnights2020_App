package com.droidknights.app2020.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["bindImgRes"])
fun bindSetImage(imgView: ImageView, resId: Int) = imgView.setImageResource(resId)