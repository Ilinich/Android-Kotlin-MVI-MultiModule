package com.begoml.testfeatures.di.dependencies

import com.begoml.common_api.di.AppProvider
import com.begoml.feature_user_account_impl.di.deps.FeatureUserAccountDependencies
import dagger.Component

@Component(dependencies = [AppProvider::class])
interface FeatureUserAccountDependenciesComponent : FeatureUserAccountDependencies