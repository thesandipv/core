package com.afterroot.utils

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build

fun Intent.isResolvable(activity: Activity, flags: Long = 0): Boolean {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        activity.packageManager.resolveActivity(
            this,
            PackageManager.ResolveInfoFlags.of(flags)
        ) != null
    } else {
        @Suppress("DEPRECATION")
        activity.packageManager.resolveActivity(this, flags.toInt()) != null
    }
}
