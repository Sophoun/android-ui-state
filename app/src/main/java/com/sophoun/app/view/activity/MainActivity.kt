package com.sophoun.app.view.activity

import android.os.Bundle
import com.sophoun.app.R
import com.sophoun.app.view.fragment.FirstFragment
import com.sophoun.app.view.fragment.RootHomeFragment
import com.sophoun.app.view.fragment.SecondFragment
import com.sophoun.navigator.FragmentStackNavigator
import com.sophoun.navigator.FragmentTabNavigator
import kotlinx.android.synthetic.main.activity_main.*

/**
 * The Main activity for application
 * this class invoke by android framework
 */
class MainActivity : AppBaseActivity() {

    private val rootHomeFragment by lazy { fragmentFactory?.instantiate(classLoader, RootHomeFragment::class.java.name)!! }
    private val firstFragment by lazy { fragmentFactory?.instantiate(classLoader, FirstFragment::class.java.name)!! }
    private val secondFragment by lazy { fragmentFactory?.instantiate(classLoader, SecondFragment::class.java.name)!! }
    lateinit var fragmentTabNavigator: FragmentTabNavigator

    val homeNav by lazy { FragmentStackNavigator(rootHomeFragment.childFragmentManager, R.id.root_home_container) }

    override fun layout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragmentTabNavigator =
            FragmentTabNavigator(
                supportFragmentManager,
                R.id.nav_host_fragment
            )

        fragmentTabNavigator.setRootFragment(listOf(
            rootHomeFragment,
            firstFragment,
            secondFragment
        ), 1)

        homeNav.setExitOn(0)
        homeNav.setHideFragment(true)
        fragmentTabNavigator.setExitOnHomeTab(true)
        fragmentTabNavigator.setChildNavigatorStack(listOf(homeNav, null, null))

        var count = 0
        bottom_navigation.setOnNavigationItemSelectedListener {
            count += 1
            when(it.itemId) {
                R.id.navHomeAction -> {
                    fragmentTabNavigator.switchTo(0)
                    true
                }
                R.id.navFirstAction -> {
                    fragmentTabNavigator.switchTo(1)
                    true
                }
                R.id.navSecondAction -> {
                    fragmentTabNavigator.switchTo(2)
                    true
                }
                else -> false
            }
        }

        fragmentTabNavigator.addOnTabChangedListener {
            bottom_navigation.selectedItemId = bottom_navigation.menu.getItem(it).itemId
        }

        if (savedInstanceState == null)
            bottom_navigation.selectedItemId = R.id.navFirstAction
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (!fragmentTabNavigator.pop()) {
            finish()
        }
    }
}
