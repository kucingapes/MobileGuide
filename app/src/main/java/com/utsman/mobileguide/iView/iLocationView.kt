/*
 * iLocationView.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 7:31 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iView

import android.location.Location

interface iLocationView {
    fun onRequestLocation(location: Location)
}