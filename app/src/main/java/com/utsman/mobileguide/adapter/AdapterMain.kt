/*
 * AdapterMain.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/20/18 11:51 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.utsman.mobileguide.DetailActivity
import com.utsman.mobileguide.R
import com.utsman.mobileguide.model.MyDocument
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView
import java.util.*

class AdapterMain(private var list: MutableList<MyDocument>) : RecyclerView.Adapter<MyHolder>() {

    private lateinit var context: Context
    private var location: Location? = null

    fun myLocation(location: Location): Location {
        this.location = location
        return location
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        context = parent.context
        return MyHolder(
            RecyclerMainUi().createView(
                AnkoContext.create(context, parent)
            )
        )
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val item = list[position]

        val cardContainer = holder.itemView.find(R.id.card_container) as CardView
        val locationHeader = holder.itemView.find(R.id.header_card_item) as ImageView
        val locationName = holder.itemView.find(R.id.name_item) as TextView
        val locationDist = holder.itemView.find(R.id.distance_item) as TextView

        val geoCoder = Geocoder(context, Locale.getDefault())
        val locationAddress = geoCoder.getFromLocation(item.location.latitude, item.location.longitude, 1)
        val jalan = locationAddress[0].thoroughfare?.replace("Jalan", "Jl.", true)
        val kota = locationAddress[0].subAdminArea?.replace("Kota", "", true)

        val objLocation = Location("")
        objLocation.latitude = item.location.latitude
        objLocation.longitude = item.location.longitude
        val distanceLocation = location?.distanceTo(objLocation)

        var distanceKm = String.format("\n%.1f Km dari sini", distanceLocation?.div(1000))
        if (distanceKm == "\nn Km dari sini") {
            distanceKm = ""
        }

        if (jalan == null) {
            locationDist.text = "$kota $distanceKm"
        } else {
            locationDist.text = "$jalan, $kota $distanceKm"
        }

        locationName.text = item.name
        Picasso.get().load(item.images[0]).into(locationHeader)
        cardContainer.setOnClickListener {
           /* context.startActivity<DetailActivity>(
                "id_document" to item.id,
                "distance" to distanceKm,
                "myLat" to myLocation?.latitude,
                "myLng" to myLocation?.longitude)*/
            context.startActivity<DetailActivity>("id_document" to item.id)
        }

    }

    class RecyclerMainUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            cardView {
                id = R.id.card_container
                lparams(matchParent, wrapContent) {
                    margin = dip(6)
                }

                imageView {
                    id = R.id.header_card_item
                    scaleType = ImageView.ScaleType.CENTER_CROP
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        transitionName = "banner"
                    }

                    setColorFilter(
                        ContextCompat.getColor(context, R.color.tint_dark),
                        android.graphics.PorterDuff.Mode.SRC_ATOP)
                }.lparams(matchParent, wrapContent) {
                    minimumHeight = dip(100)
                }

                verticalLayout {
                    textView {
                        id = R.id.name_item
                        //ViewUtils.initText(this, PLAYFAIR_BOLD)
                        textColorResource = android.R.color.white
                    }.lparams(wrapContent, wrapContent)

                    textView {
                        id = R.id.distance_item
                        //ViewUtils.initText(this, PLAYFAIR_REGULAR, 12f)
                        textColorResource = android.R.color.white
                    }.lparams(wrapContent, wrapContent) {
                        bottomMargin = dip(6)
                    }

                    gravity = Gravity.BOTTOM
                    padding = dip(8)
                }.lparams(matchParent, matchParent)
            }
        }
    }
}