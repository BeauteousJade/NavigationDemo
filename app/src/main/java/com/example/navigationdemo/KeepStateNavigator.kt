package com.example.navigationdemo

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.fragment.FragmentNavigator
import java.util.*

@Navigator.Name("KeepStateFragment")
class KeepStateNavigator(
    private val mContext: Context,
    private val mFragmentManager: FragmentManager,
    private val mContainerId: Int
) : Navigator<FragmentNavigator.Destination>() {

    private val mBackStack: Stack<Fragment> = Stack()

    override fun createDestination(): FragmentNavigator.Destination {
        return FragmentNavigator.Destination(this)
    }

    override fun navigate(
        destination: FragmentNavigator.Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination {
        return mFragmentManager.beginTransaction().run {
            var className = destination.className
            if (className[0] == '.') {
                className = mContext.packageName + className
            }
            val frag = instantiateFragment(
                mContext, mFragmentManager,
                className, args
            )
            getCurrentFragment()?.let {
                setMaxLifecycle(it, Lifecycle.State.STARTED)
            }
            frag.arguments = args

            add(mContainerId, frag)
            mBackStack.add(frag)
            commitAllowingStateLoss()

            destination
        }
    }

    override fun popBackStack(): Boolean {
        if (mBackStack.isEmpty()) {
            return false
        }
        mFragmentManager.beginTransaction().apply {
            val popFragment = mBackStack.pop()
            remove(popFragment)
            getCurrentFragment()?.run {
                setMaxLifecycle(this, Lifecycle.State.RESUMED)
            }
            commitAllowingStateLoss()
        }
        return true
    }

    private fun getCurrentFragment(): Fragment? {
        return if (mBackStack.isEmpty()) null else mBackStack.peek()
    }

    fun instantiateFragment(
        context: Context,
        fragmentManager: FragmentManager,
        className: String, args: Bundle?
    ): Fragment {
        return fragmentManager.fragmentFactory.instantiate(
            context.classLoader, className
        )
    }
}