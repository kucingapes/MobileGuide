/*
 * RouteActivity.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 9:23 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide

import android.location.Location
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.utsman.mobileguide.adapter.AdapterRoute
import com.utsman.mobileguide.helper.LocationHelper
import com.utsman.mobileguide.helper.RouteHelper
import com.utsman.mobileguide.iView.iLocationView
import com.utsman.mobileguide.iView.iRouteView
import com.utsman.mobileguide.model.trafi.RouteSegment
import com.utsman.mobileguide.model.trafi.Trafi
import com.utsman.mobileguide.presenter.LocationPresenter
import com.utsman.mobileguide.presenter.RoutePresenter
import com.utsman.mobileguide.ui.RouteUi
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.navigationIconResource
import org.jetbrains.anko.support.v4.onRefresh

class RouteActivity : BaseActivity(), iRouteView, iLocationView, AnkoLogger {

    private lateinit var locationPresenter: LocationPresenter
    private lateinit var routePresenter: RoutePresenter
    private var disposable: Disposable? = null

    private var list: MutableList<RouteSegment> = mutableListOf()
    private lateinit var routeAdapter: AdapterRoute

    private lateinit var toolbar: Toolbar
    private lateinit var recyclerRoute: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        RouteUi().setContentView(this)
        AndroidNetworking.initialize(this)
        bindId()

        locationPresenter = LocationPresenter(this, LocationHelper(this, disposable))
        locationPresenter.requestLocation()
        swipeRefresh.isRefreshing = true
        swipeRefresh.onRefresh {
            onRefresh()
        }
    }

    override fun onRequestLocation(location: Location) {
        val objLocation = Location("")
        objLocation.latitude = intent.getDoubleExtra("objLat", 0.0)
        objLocation.longitude = intent.getDoubleExtra("objLng", 0.0)

        routePresenter = RoutePresenter(this, RouteHelper(location, objLocation))
        routePresenter.requestData()

    }

    override fun onRefresh() {
        list.clear()
        routeAdapter.notifyDataSetChanged()
        routePresenter.requestData()
    }

    override fun offRefresh() {
        swipeRefresh.isRefreshing = false
    }

    override fun showDataToRecyclerView(list: MutableList<RouteSegment>) {
        val objName = intent.getStringExtra("objName")

        this.list = list
        routeAdapter = AdapterRoute(this.list, objName)
        routeAdapter.notifyDataSetChanged()

        recyclerRoute.apply {
            layoutManager = LinearLayoutManager(this@RouteActivity)
            adapter = routeAdapter
        }

        initToolbar(objName)
    }

    private fun initToolbar(objName: String?) {
        toolbar.apply {
            title = "Saran menuju $objName"
            navigationIconResource = R.drawable.ic_clear
            setNavigationOnClickListener {
                onBackPressed()
            }
        }
    }

    override fun onFailure(anError: ANError) {
        error { "my_error_route ---> ${anError.message}" }
    }

    private fun bindId() {
        toolbar = find(R.id.toolbar)
        recyclerRoute = find(R.id.route_list)
        swipeRefresh = find(R.id.swipe_refresh)
    }
}