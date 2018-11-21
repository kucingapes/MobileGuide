/*
 * LocationHelper.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 7:44 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.helper

import android.annotation.SuppressLint
import android.content.Context
import com.utsman.mobileguide.LocationUtils
import com.utsman.mobileguide.iNotice.iNoticeLocation
import io.reactivex.disposables.Disposable
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider

class LocationHelper(private var context: Context, private var disposable: Disposable?) :
    iNoticeLocation {

    @SuppressLint("MissingPermission")
    override fun getNoticeLocation(onLocationListener: iNoticeLocation.OnLocationListener) {
        val locationProvider = ReactiveLocationProvider(context)
        disposable = locationProvider.getUpdatedLocation(LocationUtils.request())
            .subscribe { t ->
                onLocationListener.onUpdateLocation(t)
            }
    }
}