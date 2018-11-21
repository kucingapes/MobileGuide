/*
 * RoutePresenter.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/22/18 2:47 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.presenter

import com.androidnetworking.error.ANError
import com.utsman.mobileguide.iNotice.iNoticeRoute
import com.utsman.mobileguide.iView.iRouteView
import com.utsman.mobileguide.model.trafi.RouteSegment

class RoutePresenter(private var routeView: iRouteView, private var noticeRoute: iNoticeRoute) : iRoutePresenter,
    iNoticeRoute.OnCompleteListener {
    override fun requestData() {
        noticeRoute.getNoticeRoute(this)
    }

    override fun onComplete(list: MutableList<RouteSegment>) {
        routeView.showDataToRecyclerView(list)
        routeView.offRefresh()
    }

    override fun onError(anError: ANError) {
        routeView.onFailure(anError)
        routeView.offRefresh()
    }
}