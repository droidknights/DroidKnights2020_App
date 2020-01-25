package com.droidknights.app2020.ext

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

inline fun <reified T : ViewModel> ComponentActivity.assistedActivityViewModels(
    crossinline body: () -> ViewModelProvider.Factory
): Lazy<T> {
    return viewModels { body() }
}

inline fun <reified VM : ViewModel> Fragment.assistedViewModels(
    crossinline body: () -> ViewModelProvider.Factory
): Lazy<VM> {
    return viewModels { body() }
}

inline fun <reified VM : ViewModel> Fragment.assistedActivityViewModels(
    crossinline body: () -> ViewModelProvider.Factory
): Lazy<VM> {
    return activityViewModels { body() }
}

inline fun <reified VM : ViewModel> Fragment.assistedParentViewModels(
    crossinline body: () -> ViewModelProvider.Factory
): Lazy<VM> {
    return requireParentFragment().viewModels { body() }
}