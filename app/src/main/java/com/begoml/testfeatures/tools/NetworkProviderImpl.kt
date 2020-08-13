package com.begoml.testfeatures.tools

import android.content.Context
import com.begoml.common_api.providers.NetworkProvider
import com.begoml.tools.extension.isNetworkAvailable
import javax.inject.Inject

class NetworkProviderImpl @Inject constructor(private val context: Context) : NetworkProvider {

    override fun isNetworkAvailable(): Boolean {
        return context.isNetworkAvailable()
    }
}