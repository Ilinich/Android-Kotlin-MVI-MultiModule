package com.begoml.datasource_impl.local

import android.content.SharedPreferences
import com.begoml.datasource_api.local.LocalAccountDataSource
import javax.inject.Inject

class LocalAccountDataSourceImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LocalAccountDataSource {
}