/*
 * Copyright (C) 2019-2020 Sandip Vaghela
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *          http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package fr.dasilvacampos.network.monitoring.core

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import fr.dasilvacampos.network.monitoring.NetworkConnectivityListener
import fr.dasilvacampos.network.monitoring.NetworkState

/**
 * This is the implementation Application.ActivityLifecycleCallbacksImp,
 * it calls the methods of NetworkConnectivityListener in the activity implementing it,
 * thus enabling
 * @see Application.ActivityLifecycleCallbacks
 */
internal class ActivityLifecycleCallbacksImp(private val networkState: NetworkState) :
    Application.ActivityLifecycleCallbacks {


    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, p1: Bundle) {}

    override fun onActivityStopped(activity: Activity) {}

    override fun onActivityDestroyed(activity: Activity) {}

    override fun onActivityCreated(activity: Activity, p1: Bundle?) = safeRun(TAG) {
        if (activity !is LifecycleOwner) return

        if (activity is FragmentActivity)
            addLifecycleCallbackToFragments(
                activity,
                networkState
            )

        if (activity !is NetworkConnectivityListener || !activity.shouldBeCalled) return

        activity.onListenerCreated()

    }

    override fun onActivityResumed(activity: Activity) = safeRun {
        if (activity !is LifecycleOwner) return
        if (activity !is NetworkConnectivityListener) return

        activity.onListenerResume(networkState)
    }


    private fun addLifecycleCallbackToFragments(activity: FragmentActivity, networkState: NetworkState) = safeRun(
        TAG
    ) {

        val callback = object : FragmentManager.FragmentLifecycleCallbacks() {

            override fun onFragmentCreated(fm: FragmentManager, fragment: Fragment, savedInstanceState: Bundle?) {
                if (fragment !is NetworkConnectivityListener || !fragment.shouldBeCalled) return
                fragment.onListenerCreated()
            }

            override fun onFragmentResumed(fm: FragmentManager, fragment: Fragment) {
                if (fragment is NetworkConnectivityListener)
                    fragment.onListenerResume(networkState)
            }
        }

        activity.supportFragmentManager.registerFragmentLifecycleCallbacks(callback, true)
    }

    companion object {
        const val TAG = "ActivityCallbacks"

    }
}