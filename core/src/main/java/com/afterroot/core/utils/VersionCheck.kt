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

package com.afterroot.core.utils

data class VersionInfo(
    var currentVersion: Long = 0,
    var latestLink: String? = null,
    var latestVersion: Long = 0
)

class VersionCheck {
    val version = VersionInfo()

    fun setLatestVersion(latest: Long) {
        version.latestVersion = latest
    }

    fun setLatestVersionLink(link: String) {
        version.latestLink = link
    }

    fun setCurrentVersion(current: Long) {
        version.currentVersion = current
    }

    inline fun onUpdateAvailable(crossinline onUpdate: (VersionInfo) -> Unit) {
        if (version.currentVersion == 0L || version.latestVersion == 0L) {
            throw IllegalStateException("Parameter 'currentVersion' or 'latestVersion' not set.")
        }
        if (version.latestVersion > version.currentVersion) {
            onUpdate(version)
        }
    }
}
