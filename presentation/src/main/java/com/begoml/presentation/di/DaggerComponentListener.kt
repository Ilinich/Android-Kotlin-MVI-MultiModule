package com.begoml.presentation.di

interface DaggerComponentListener<Component> {

    fun injectComponent(component: Component)

    fun createComponent(): Component
}