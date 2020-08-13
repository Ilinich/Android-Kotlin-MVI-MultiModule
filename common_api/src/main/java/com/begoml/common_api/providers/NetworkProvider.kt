package com.begoml.common_api.providers

interface NetworkProvider {

    fun isNetworkAvailable(): Boolean
}