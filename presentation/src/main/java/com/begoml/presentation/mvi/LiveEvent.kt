package com.begoml.presentation.mvi

import androidx.annotation.MainThread
import androidx.collection.ArraySet
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer

class LiveEvent<News> : MediatorLiveData<News>() {

    private val observers = ArraySet<ObserverWrapper<in News>>()

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in News>) {
        val wrapper = ObserverWrapper(observer)
        observers.add(wrapper)
        super.observe(owner, wrapper)
    }

    @MainThread
    override fun removeObserver(observer: Observer<in News>) {
        if (observers.remove(observer)) {
            super.removeObserver(observer)
            return
        }
        val iterator = observers.iterator()
        while (iterator.hasNext()) {
            val wrapper = iterator.next()
            if (wrapper.observer == observer) {
                iterator.remove()
                super.removeObserver(wrapper)
                break
            }
        }
    }

    @MainThread
    override fun setValue(news: News?) {
        observers.forEach { it.newValue() }
        super.setValue(news)
    }

    private class ObserverWrapper<News>(val observer: Observer<News>) : Observer<News> {

        private var pending = false

        override fun onChanged(news: News?) {
            if (pending) {
                pending = false
                observer.onChanged(news)
            }
        }

        fun newValue() {
            pending = true
        }
    }
}