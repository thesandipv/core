/*
 * Copyright (C) 2020 Sandip Vaghela
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

package fr.dasilvacampos.network.monitoring

/**
 * When implemented by an Activity, it add hooks to network events thanks to ActivityLifecycleCallbacks
 * @see android.app.Application.ActivityLifecycleCallbacks
 */
interface NetworkConnectivityListener {

    /**
     * Put this at false to disable hooks ( enabled by default )
     */
    val shouldBeCalled: Boolean
        get() = true

    /**
     * Put this at false to disable hooks on resume ( enabled by default )
     */
    val checkOnResume: Boolean
        get() = true

    val isConnected: Boolean
        get() = NetworkStateHolder.isConnected


    fun networkConnectivityChanged(event: Event)
}