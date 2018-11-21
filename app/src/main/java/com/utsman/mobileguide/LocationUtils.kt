/*
 * LocationUtils.ktt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 6:36 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide

import com.google.android.gms.location.LocationRequest

object LocationUtils {
    fun request() : LocationRequest {
        return LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setNumUpdates(5)
            .setInterval(100)
    }
}