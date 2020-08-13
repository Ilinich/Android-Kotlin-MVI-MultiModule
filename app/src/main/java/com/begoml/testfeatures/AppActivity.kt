package com.begoml.testfeatures

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.begoml.testfeatures.di.AppComponent
import com.begoml.common_api.navigation.AppFlowCoordinator
import com.begoml.common_api.navigation.NavigationGlobal
import com.begoml.presentation.view.BackPressedListener
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command
import javax.inject.Inject

class AppActivity : AppCompatActivity(R.layout.app_activity) {

    @Inject
    @field:[NavigationGlobal]
    lateinit var navigatorHolder: NavigatorHolder

    @Inject
    lateinit var flowCoordinator: AppFlowCoordinator

    private val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(R.id.container)

    private val navigator by lazy {
        object : SupportAppNavigator(this, supportFragmentManager, R.id.container) {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        AppComponent.get().inject(this)

        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            flowCoordinator.startApp()
        }

    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()

        super.onPause()
    }

    override fun onBackPressed() {
        if ((currentFragment as? BackPressedListener)?.onBackPressed() == false) {
            flowCoordinator.finishFeature()
        }
    }
}