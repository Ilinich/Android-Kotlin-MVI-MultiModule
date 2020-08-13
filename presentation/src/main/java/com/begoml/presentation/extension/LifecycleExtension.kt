package com.begoml.presentation.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T , L : LiveData<T>> LifecycleOwner.observeViewState(
    liveData: L,
    body: (T) -> Unit
) = liveData.observe(this, Observer { stateView ->
    stateView?.let {
        body(it)
    }
})