/*
 * iNoticeLocation.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 7:44 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iNotice

import android.location.Location

interface iNoticeLocation {
    interface OnLocationListener {
        fun onUpdateLocation(location: Location)
    }

    fun getNoticeLocation(onLocationListener: OnLocationListener)
}