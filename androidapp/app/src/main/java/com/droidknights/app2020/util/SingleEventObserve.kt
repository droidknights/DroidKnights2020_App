package com.droidknights.app2020.util

import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.droidknights.app2020.common.Event

@MainThread
inline fun <T> LiveData<Event<T>>.eventObserve(
    owner: LifecycleOwner,
    crossinline onChanged: (T) -> Unit
): Observer<Event<T>> {
    val wrappedObserver = Observer<Event<T>> { t ->
        t.getContentIfNotHandled()?.let {
            onChanged.invoke(it)
        }
    }
    observe(owner, wrappedObserver)
    return wrappedObserver
}
