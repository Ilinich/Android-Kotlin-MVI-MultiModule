package com.begoml.testfeatures.di.modules

import com.begoml.datasource_api.local.LocalAccountDataSource
import com.begoml.datasource_api.remote.RemoteAccountDataSource
import com.begoml.datasource_impl.local.LocalAccountDataSourceImpl
import com.begoml.datasource_impl.remote.RemoteAccountDataSourceImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface DataSourceModule {

    @Binds
    @Singleton
    fun bindsLocalAccountDataSource(localAccountDataSource: LocalAccountDataSourceImpl): LocalAccountDataSource

    @Binds
    @Singleton
    fun bindsRemoteAccountDataSource(remoteAccountDataSource: RemoteAccountDataSourceImpl): RemoteAccountDataSource

}