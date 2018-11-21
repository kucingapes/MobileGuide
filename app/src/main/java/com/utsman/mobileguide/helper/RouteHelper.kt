/*
 * RouteHelper.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/22/18 2:50 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.helper

import android.location.Location
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.utsman.mobileguide.BuildConfig
import com.utsman.mobileguide.iNotice.iNoticeRoute
import com.utsman.mobileguide.model.trafi.Trafi

class RouteHelper(private val myLocation: Location, private val objLocation: Location) : iNoticeRoute {
    override fun getNoticeRoute(onCompleteListener: iNoticeRoute.OnCompleteListener) {
        AndroidNetworking.get(BuildConfig.TrafiBaseUrl)
            .addQueryParameter("start_lat", "${myLocation.latitude}")
            .addQueryParameter("start_lng", "${myLocation.longitude}")
            .addQueryParameter("end_lat", "${objLocation.latitude}")
            .addQueryParameter("end_lng", "${objLocation.longitude}")
            .addQueryParameter("is_arrival", "false")
            .addQueryParameter("api_key", BuildConfig.TrafiApiKey)
            .build()
            .getAsObject(Trafi::class.java, object : ParsedRequestListener<Trafi> {
                override fun onResponse(response: Trafi) {
                    onCompleteListener.onComplete(response.Routes[0].RouteSegments)
                }

                override fun onError(anError: ANError) {
                    onCompleteListener.onError(anError)
                }

            })
    }
}