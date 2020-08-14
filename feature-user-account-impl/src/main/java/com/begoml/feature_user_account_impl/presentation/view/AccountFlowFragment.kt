package com.begoml.feature_user_account_impl.presentation.view

import android.os.Bundle
import android.view.View
import com.begoml.feature_user_account_impl.di.AccountFlowCoordinator
import com.begoml.feature_user_account_impl.di.components.UserAccountFlowComponent
import com.begoml.feature_user_account_impl.navigation.FlowCoordinator
import com.begoml.presentation.di.DaggerComponentListener
import com.begoml.presentation.extension.addDaggerComponentLifecycle
import com.begoml.presentation.view.FlowFragment
import javax.inject.Inject

class AccountFlowFragment : FlowFragment(), DaggerComponentListener<UserAccountFlowComponent> {

    @Inject
    @field:[AccountFlowCoordinator]
    lateinit var flowCoordinator: FlowCoordinator

    override fun onCreate(savedInstanceState: Bundle?) {
        addDaggerComponentLifecycle<UserAccountFlowComponent>()

        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            flowCoordinator.setLaunchScreen()
        }
    }

    override fun injectComponent(component: UserAccountFlowComponent) {
        component.inject(this)
    }

    override fun createComponent(): UserAccountFlowComponent {
        featureProxyInjector.injectUserAccountFeature()
        return UserAccountFlowComponent.get()
    }
}