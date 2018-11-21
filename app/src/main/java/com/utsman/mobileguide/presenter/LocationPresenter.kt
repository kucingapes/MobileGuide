/*
 * LocationPresenter.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 7:36 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.presenter

import android.location.Location
import com.utsman.mobileguide.iView.iLocationView
import com.utsman.mobileguide.iNotice.iNoticeLocation

class LocationPresenter(private var locationView: iLocationView, private var noticeLocation: iNoticeLocation) :
    iLocationPresenter, iNoticeLocation.OnLocationListener {
    override fun requestLocation() {
        noticeLocation.getNoticeLocation(this)
    }

    override fun onUpdateLocation(location: Location) {
        locationView.onRequestLocation(location)
    }
}