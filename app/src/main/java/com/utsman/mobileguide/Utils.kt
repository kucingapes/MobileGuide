/*
 * Utils.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 8:17 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide

import android.content.Context

object Utils {
    fun anGetStatusBarHeight(context: Context): Int {
        var result = 0
        val resourceId = context.resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = context.resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}