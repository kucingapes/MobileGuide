/*
 * BaseMapActivity.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 8:36 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide

import android.annotation.SuppressLint
import com.google.android.gms.maps.MapView

@SuppressLint("Registered")
open class BaseMapActivity : BaseActivity() {

    lateinit var mapsView: MapView

    override fun onResume() {
        super.onResume()
        mapsView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapsView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapsView.onStop()
    }

    override fun onPause() {
        mapsView.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mapsView.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapsView.onLowMemory()
    }
}