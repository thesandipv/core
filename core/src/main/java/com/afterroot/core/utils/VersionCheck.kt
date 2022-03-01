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

import com.afterroot.core.data.model.VersionInfo

class VersionCheck(private var versionInfo: VersionInfo = VersionInfo()) {

    fun setLatestVersion(latest: Int) {
        versionInfo = versionInfo.copy(latestVersion = latest)
    }

    fun setLatestVersionLink(link: String) {
        versionInfo = versionInfo.copy(latestLink = link)
    }

    fun setCurrentVersion(current: Int) {
        versionInfo = versionInfo.copy(currentVersion = current)
    }

    fun setDisabledVersions(disabled: List<Int>) {
        versionInfo = versionInfo.copy(disabledVersions = disabled)
    }

    fun onUpdateAvailable(onUpdate: (VersionInfo) -> Unit) {
        if (versionInfo.currentVersion == 0 || versionInfo.latestVersion == 0) {
            throw IllegalStateException("Parameter 'currentVersion' or 'latestVersion' not set.")
        }
        if (versionInfo.latestVersion > versionInfo.currentVersion && !versionInfo.isDisabled()) {
            onUpdate(versionInfo)
        }
    }

    fun onVersionDisabled(onDisabled: (VersionInfo) -> Unit) {
        if (versionInfo.isDisabled()) {
            onDisabled(versionInfo)
        }
    }
}
