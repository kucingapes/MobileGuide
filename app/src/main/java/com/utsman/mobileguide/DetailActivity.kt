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
import com.utsman.mobileguide.model.MyDocument
import com.utsman.mobileguide.ui.DetailUi
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast
import java.util.*

class DetailActivity : BaseMapActivity() {

    private val DEFAULT_DOUBLE: Double = 0.0

    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbar: CollapsingToolbarLayout
    private lateinit var imageBanner: ImageView
    private lateinit var titleText: TextView
    private lateinit var addressText: TextView
    private lateinit var textDescription: TextView
    private lateinit var images: RecyclerView
    private lateinit var lokasiTitle: TextView
    private lateinit var btnRoute: CardView

    private lateinit var ratingView: LinearLayout
    private lateinit var ratingText: TextView
    private lateinit var ratingStar: ImageView

    private lateinit var googleMap: GoogleMap

    private var id: String? = null

    private var objLat: Double? = DEFAULT_DOUBLE
    private var objLng: Double? = DEFAULT_DOUBLE

    private var db: FirebaseFirestore? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailUi().setContentView(this)
        initId()

        id = intent.getStringExtra("id_document")
        db = FirebaseFirestore.getInstance()

        initToolbar()

        prepareDb()
        mapsView.onCreate(savedInstanceState)
    }

    private fun prepareDb() {
        id?.let {
            db?.collection("lokasi")?.document(it)?.get()
                ?.addOnSuccessListener { task ->
                    val document = task.toObject(MyDocument::class.java)
                    document?.let { it ->
                        setupDb(it)
                    }
                }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupDb(document: MyDocument) {

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

        mapsView.getMapAsync { p0 ->
            googleMap = p0
            val latLng = LatLng(document.location.latitude, document.location.longitude)
            val marker = googleMap.addMarker(MarkerOptions().position(latLng).title(document.name))
            marker.showInfoWindow()
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18f))
            googleMap.uiSettings.isScrollGesturesEnabled = false
        }

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
        lokasiTitle = find(R.id.lokasi_title)
        mapsView = find(R.id.maps_view)
        btnRoute = find(R.id.route_btn)

        ratingView = find(R.id.rating_view)
        ratingText = find(R.id.text_rating)
        ratingStar = find(R.id.star_rating)
    }
}