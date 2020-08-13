package com.begoml.presentation.extension

import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.begoml.presentation.di.DaggerComponentListener

fun <Component : Any> Fragment.addDaggerComponentLifecycle() {
    lifecycle.addObserver(CreateClearDaggerComponent(this as DaggerComponentListener<Component>))
}

class CreateClearDaggerComponent<Component : Any>(private val daggerComponent: DaggerComponentListener<Component>) : LifecycleObserver {

    private lateinit var component: Component

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        component = daggerComponent.createComponent()
        daggerComponent.injectComponent(component)
    }
}

fun Fragment.isNeedToClearComponent(): Boolean =
    when {
        activity?.isChangingConfigurations == true -> false
        activity?.isFinishing == true -> true
        else -> true
    }
