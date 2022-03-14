/*
 * Copyright (C) 2016-2022 Sandip Vaghela
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.afterroot.core.utils

import android.content.Context
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.ColorInt
import com.google.android.material.color.MaterialColors

fun Context.getMaterialColor(@AttrRes colorAttr: Int, errorMessageComponent: String = ""): Int {
    return MaterialColors.getColor(this, colorAttr, errorMessageComponent)
}

fun View.getMaterialColor(@AttrRes colorAttr: Int, @ColorInt defaultValue: Int? = null): Int {
    return if (defaultValue == null) {
        MaterialColors.getColor(this, colorAttr)
    } else MaterialColors.getColor(this, colorAttr, defaultValue)
}
