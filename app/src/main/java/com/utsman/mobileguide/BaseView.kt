/*
 * BaseView.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 8:19 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide

import android.view.ViewManager
import com.google.android.gms.maps.MapView
import org.jetbrains.anko.custom.ankoView

object BaseView {
    fun ViewManager.mapsView() = ankoView(::MapView, 0) {}
}