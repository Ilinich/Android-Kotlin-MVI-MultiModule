package com.begoml.common_api.providers

import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.StringRes

interface ResourceProvider {

    val deviceId: String

    val packageName: String

    fun getString(@StringRes res: Int): String

    fun getString(@StringRes res: Int, vararg args: Any): String

    fun getStringArray(@ArrayRes res: Int): List<String>

    fun getColor(@ColorRes res: Int): Int
}