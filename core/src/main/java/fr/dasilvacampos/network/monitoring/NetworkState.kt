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

import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi

/**
 * Enables synchronous and asynchronous connectivity state checking thanks to LiveData and stored states.
 * @see isConnected to get the instance connectivity state
 * @see NetworkEvents to observe connectivity changes
 */
@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
interface NetworkState {

    /**
     * Stored connectivity state of the network
     * True if the device as access the the network
     */
    val isConnected: Boolean

    /**
     * The network being used by the device
     */
    val network: Network?

    /**
     * Network Capabilities
     */
    val networkCapabilities: NetworkCapabilities?

    /**
     * Link Properties
     */
    val linkProperties: LinkProperties?

    /**
     * Check if the network is Wifi ( shortcut )
     */
    val isWifi: Boolean
        get() = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false

    /**
     * Check if the network is Mobile ( shortcut )
     */
    val isMobile: Boolean
        get() = networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false

    /**
     * Get the interface name ( shortcut )
     */
    val interfaceName: String?
        get() = linkProperties?.interfaceName
}