package com.example.navigationdemo

import android.os.Bundle
import android.view.View
import androidx.annotation.NavigationRes
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment

class CustomNavHostFragment : NavHostFragment() {

    companion object {
        private const val KEY_GRAPH_ID = "android-support-nav:fragment:graphId"
        private const val KEY_START_DESTINATION_ARGS =
            "android-support-nav:fragment:startDestinationArgs"
        private const val KEY_NAV_CONTROLLER_STATE =
            "android-support-nav:fragment:navControllerState"
        private const val KEY_DEFAULT_NAV_HOST = "android-support-nav:fragment:defaultHost"

        fun create(@NavigationRes graphResId: Int): NavHostFragment {
            return create(graphResId, null)
        }

        fun create(
            @NavigationRes graphResId: Int,
            startDestinationArgs: Bundle?
        ): NavHostFragment {
            var b: Bundle? = null
            if (graphResId != 0) {
                b = Bundle()
                b.putInt(KEY_GRAPH_ID, graphResId)
            }
            if (startDestinationArgs != null) {
                if (b == null) {
                    b = Bundle()
                }
                b.putBundle(KEY_START_DESTINATION_ARGS, startDestinationArgs)
            }
            val result = CustomNavHostFragment()
            if (b != null) {
                result.arguments = b
            }
            return result
        }
    }


    override fun onCreateNavController(navController: NavController) {
        super.onCreateNavController(navController)
        navController.navigatorProvider.addNavigator(
            KeepStateNavigator(
                requireContext(),
                childFragmentManager,
                getContainerId()
            )
        )
    }

    private fun getContainerId(): Int {
        return if (id != 0 && id != View.NO_ID) id else R.id.nav_host_fragment_container
    }
}