package com.begoml.presentation.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.begoml.common_api.CharityApp
import com.begoml.common_api.navigation.AppFlowCoordinator
import com.begoml.presentation.R
import com.begoml.presentation.di.NavigationFlow
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

abstract class FlowFragment : Fragment(R.layout.layout_container), BackPressedListener {

    private val currentFragment
        get() = childFragmentManager.findFragmentById(R.id.container)

    @Inject
    @field:[NavigationFlow]
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var appFlowCoordinator: AppFlowCoordinator

    val featureProxyInjector by lazy { (context?.applicationContext as CharityApp).featureProxyInjector }

    private val navigator: Navigator by lazy {
        object : SupportAppNavigator(activity, childFragmentManager, R.id.container) {
            override fun activityBack() {
                (parentFragment as? ChildBackPressedListener)?.childBackPressed()?.let {
                    appFlowCoordinator.finishFeature()
                }
            }

            override fun setupFragmentTransaction(
                command: Command?,
                currentFragment: Fragment?,
                nextFragment: Fragment?,
                fragmentTransaction: FragmentTransaction
            ) {
                // Fix incorrect order lifecycle callback of MainFragment
                fragmentTransaction.setReorderingAllowed(true)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()

        super.onPause()
    }

    override fun onBackPressed(): Boolean {
        return (currentFragment as? BackPressedListener)?.onBackPressed() ?: false
    }

}