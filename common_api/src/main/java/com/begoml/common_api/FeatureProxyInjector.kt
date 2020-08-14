package com.begoml.common_api

interface FeatureProxyInjector {

    fun injectUserAccountFeature()

    fun clearUserAccountFeature()


    fun injectNewsFeedFeature()
    fun clearNewsFeedFeature()
}