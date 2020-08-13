package com.begoml.presentation.view

interface ChildBackPressedListener {
    fun childBackPressed(): Boolean
}

interface BackPressedListener {

    fun onBackPressed(): Boolean
}