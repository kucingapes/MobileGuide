/*
 * DetailActivity.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 8:09 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide

import android.annotation.SuppressLint
import android.location.Geocoder
import android.os.Bundle
import android.support.design.widget.CollapsingToolbarLayout
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import com.utsman.mobileguide.adapter.AdapterImages
import com.utsman.mobileguide.helper.SingleIdHelper
import com.utsman.mobileguide.iView.iSingleView
import com.utsman.mobileguide.model.firestore.Document
import com.utsman.mobileguide.presenter.SinglePresenter
import com.utsman.mobileguide.ui.DetailUi
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import java.lang.Exception
import java.util.*

class DetailActivity : BaseMapActivity(), iSingleView {

    private val DEFAULT_DOUBLE: Double = 0.0

    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var imageBanner: ImageView
    private lateinit var titleText: TextView
    private lateinit var addressText: TextView
    private lateinit var textDescription: TextView
    private lateinit var images: RecyclerView
    private lateinit var titleLocation: TextView
    private lateinit var btnRoute: CardView

    private lateinit var ratingView: LinearLayout
    private lateinit var ratingText: TextView
    private lateinit var ratingStar: ImageView

    private lateinit var googleMap: GoogleMap

    private var objLat: Double? = DEFAULT_DOUBLE
    private var objLng: Double? = DEFAULT_DOUBLE

    private lateinit var singlePresenter: SinglePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailUi().setContentView(this)
        initId()

        val id = intent.getStringExtra("id_document")
        val db = FirebaseFirestore.getInstance()

        initToolbar()
        singlePresenter = SinglePresenter(this, SingleIdHelper(db))
        singlePresenter.requestData(id)

        mapsView.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onPrepareById(document: Document) {
        objLat = document.location.latitude
        objLng = document.location.longitude

        val geoCoder = Geocoder(this, Locale.getDefault())
        val locationAddress = geoCoder.getFromLocation(document.location.latitude, document.location.longitude, 1)
        var address = "${locationAddress[0].thoroughfare}\n"
        val city = locationAddress[0].subAdminArea

        if (address == "null\n") {
            address = ""
        }

        Picasso.get().load(document.images[0]).into(imageBanner)
        titleText.text = document.name
        textDescription.text = document.desc
        addressText.text = "$address$city"
        titleLocation.text = intent.getStringExtra("distance")


        btnRoute.setOnClickListener {
            startActivity<RouteActivity>(
                "objLat" to objLat,
                "objLng" to objLng,
                "objName" to document.name)
        }

        images.adapter = AdapterImages(document.images)
        mapAsync(document)
    }

    private fun mapAsync(document: Document) {
        mapsView.getMapAsync { task ->
            googleMap = task
            val latLng = LatLng(document.location.latitude, document.location.longitude)
            val marker = googleMap.addMarker(MarkerOptions().position(latLng).title(document.name))
            marker.showInfoWindow()
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
            googleMap.uiSettings.isScrollGesturesEnabled = false
        }
    }

    override fun onPrepareError(exception: Exception) {
        toast("${exception.message}")
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }

    private fun initId() {
        toolbar = find(R.id.toolbar)
        collapsingToolbar = find(R.id.collapsing_toolbar)
        imageBanner = find(R.id.img_banner)
        titleText = find(R.id.title)
        addressText = find(R.id.address)
        textDescription = find(R.id.desc_item)
        images = find(R.id.images)
        titleLocation = find(R.id.lokasi_title)
        mapsView = find(R.id.maps_view)
        btnRoute = find(R.id.route_btn)

        ratingView = find(R.id.rating_view)
        ratingText = find(R.id.text_rating)
        ratingStar = find(R.id.star_rating)
    }
}