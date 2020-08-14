package com.begoml.feature_user_account_impl.di.components

import com.begoml.feature_user_account_api.di.UserAccountProvider
import com.begoml.feature_user_account_impl.di.deps.FeatureUserAccountDependencies
import com.begoml.feature_user_account_impl.di.modules.AccountFlowModule
import com.begoml.feature_user_account_impl.presentation.view.AccountFlowFragment
import com.begoml.presentation.di.FeatureNavigationModule
import com.begoml.presentation.exception.InitComponentException
import dagger.Component

@Component(
    modules = [
        AccountFlowModule::class,
        FeatureNavigationModule::class
    ],
    dependencies = [FeatureUserAccountDependencies::class]
)
interface UserAccountFlowComponent : UserAccountProvider {

    fun inject(fragment: AccountFlowFragment)

    companion object {

        private var component: UserAccountFlowComponent? = null

        fun init(dependencies: FeatureUserAccountDependencies) {
            component ?: DaggerUserAccountFlowComponent.builder()
                .featureUserAccountDependencies(dependencies)
                .build().apply {
                    component = this
                }
        }

        fun get(): UserAccountFlowComponent = component ?: throw InitComponentException()

        fun clear() {
            component = null
        }
    }
}