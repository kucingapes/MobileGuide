/*
 * iRouteView.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/22/18 2:36 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iView

import com.androidnetworking.error.ANError
import com.utsman.mobileguide.model.trafi.RouteSegment

interface iRouteView {
    fun onRefresh()
    fun offRefresh()
    fun showDataToRecyclerView(list: MutableList<RouteSegment>)
    fun onFailure(anError: ANError)
}