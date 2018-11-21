/*
 * FragmentHome.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/20/18 9:52 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.fragment

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.IntentSender
import android.location.Location
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.OrientationHelper
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.utsman.mobileguide.R
import com.utsman.mobileguide.adapter.AdapterMain
import com.utsman.mobileguide.fragment.ui.HomeUi
import com.utsman.mobileguide.helper.FirestoreHelper
import com.utsman.mobileguide.helper.LocationHelper
import com.utsman.mobileguide.iView.iLocationView
import com.utsman.mobileguide.iView.iMainView
import com.utsman.mobileguide.model.MyDocument
import com.utsman.mobileguide.presenter.LocationPresenter
import com.utsman.mobileguide.presenter.MainPresenter
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.onRefresh
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions
import java.lang.Exception

@RuntimePermissions
class FragmentHome : Fragment(), iMainView, iLocationView, AnkoLogger {

    private var list: MutableList<MyDocument> = mutableListOf()

    private lateinit var presenter: MainPresenter
    private lateinit var locationPresenter: LocationPresenter
    private lateinit var adapterMain: AdapterMain

    private lateinit var mainRecycler: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    private var disposable: Disposable? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val ui = context?.let {
            AnkoContext.create(it, this)
        }?.let {
            HomeUi<Fragment>().createView(it)
        }

        ui?.let {
            mainRecycler = it.find(R.id.recycler_main)
            swipeRefresh = it.find(R.id.swipe_refresh)
        }

        swipeRefresh.isRefreshing = true
        swipeRefresh.onRefresh {
            onRefresh()
        }

        callDatabaseWithPermissionCheck()
        return ui
    }


    @SuppressLint("MissingPermission")
    @NeedsPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    fun callDatabase() {
        presenter = MainPresenter(this, FirestoreHelper())
        presenter.requestData()
    }

    override fun onRefresh() {
        list.clear()
        adapterMain.notifyDataSetChanged()
        presenter.requestData()
    }

    override fun offRefresh() {
        swipeRefresh.isRefreshing = false
    }

    override fun showDataToRecyclerView(list: MutableList<MyDocument>) {
        this.list = list
        adapterMain = AdapterMain(this.list)
        adapterMain.notifyDataSetChanged()

        mainRecycler.apply {
            layoutManager = StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL)
            adapter = adapterMain
        }

        locationPresenter = LocationPresenter(this,
            LocationHelper(requireContext(), disposable)
        )
        locationPresenter.requestLocation()

    }

    override fun onFailure(onFailureListener: Exception) {
        error { "my_error ---> ${onFailureListener.message}" }
    }


    override fun onRequestLocation(location: Location) {
        adapterMain.myLocation(location)
        sortByDistance(list, location)
        adapterMain.notifyDataSetChanged()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        onRequestPermissionsResult(requestCode, grantResults)
    }

    @SuppressLint("MissingPermission", "CheckResult")
    private fun requestLocation() {
        val request = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            .setNumUpdates(5)
            .setInterval(100)

        val builder = LocationSettingsRequest.Builder()
            .addLocationRequest(request)
        val client = LocationServices.getSettingsClient(requireActivity())
        val task = client.checkLocationSettings(builder.build())

        task.addOnFailureListener { exception ->
            if (exception is ResolvableApiException) {
                try {
                    exception.startResolutionForResult(
                        requireActivity(),
                        2324
                    )
                } catch (sendEx: IntentSender.SendIntentException) {
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_OK) {
            if (resultCode == 2324) {
                swipeRefresh.isRefreshing = true
            }
        }

    }

    private fun sortByDistance(list: MutableList<MyDocument>, location: Location): MutableList<MyDocument> {
        list.sortWith(Comparator { model1, model2 ->
            val objLocation1 = Location("")
            objLocation1.latitude = model1.location.latitude
            objLocation1.longitude = model1.location.longitude

            val objLocation2 = Location("")
            objLocation2.latitude = model2.location.latitude
            objLocation2.longitude = model2.location.longitude

            val distance1 = location.distanceTo(objLocation1).toInt()
            val distance2 = location.distanceTo(objLocation2).toInt()
            distance1 - distance2
        })
        return list
    }

}