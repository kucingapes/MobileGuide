/*
 * iNoticeRoute.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/22/18 2:44 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iNotice

import com.androidnetworking.error.ANError
import com.utsman.mobileguide.model.trafi.RouteSegment

interface iNoticeRoute {
    interface OnCompleteListener {
        fun onComplete(list: MutableList<RouteSegment>)
        fun onError(anError: ANError)
    }

    fun getNoticeRoute(onCompleteListener: OnCompleteListener)
}