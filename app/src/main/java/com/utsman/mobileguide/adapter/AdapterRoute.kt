/*
 * AdapterRoute.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/16/18 6:49 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.TypedValue
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.utsman.mobileguide.R
import com.utsman.mobileguide.adapter.viewHolder.Holder
import com.utsman.mobileguide.model.trafi.RouteSegment
import org.jetbrains.anko.*

class AdapterRoute(private var routes: MutableList<RouteSegment>, private val objectName: String) :
    RecyclerView.Adapter<Holder>() {
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        context = parent.context
        return Holder(
            RouteItemUi().createView(
                AnkoContext.create(context, parent)
            )
        )
    }

    override fun getItemCount(): Int = routes.size

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val route = routes[position]
        val routeStartStop = holder.itemView.find(R.id.startStop) as TextView
        val routeTransport = holder.itemView.find(R.id.route_transport) as TextView
        val routeDistance = holder.itemView.find(R.id.route_distance) as TextView
        val routeDuration = holder.itemView.find(R.id.route_duration) as TextView
        val artTransport = holder.itemView.find(R.id.artTransport) as ImageView
        val artEnd = holder.itemView.find(R.id.artEnd) as ImageView
        val artLine = holder.itemView.find(R.id.artLine) as ImageView
        val container = holder.itemView.find(R.id.container) as LinearLayout

        routeStartStop.text = "${route.StartPoint.Name} menuju ${route.EndPoint.Name}"
        artEnd.visibility = View.GONE
        if (route.StartPoint.Name == "") {
            routeStartStop.text = "Menuju ${route.EndPoint.Name}"
            artEnd.visibility = View.GONE
        }
        if (route.EndPoint.Name == "") {
            routeStartStop.text = "${route.StartPoint.Name} menuju $objectName"
            artEnd.visibility = View.VISIBLE
            container.layoutParams.height =
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160F, context.resources.displayMetrics).toInt()
        }
        if (route.StartPoint.Name == "" && route.EndPoint.Name == "") {
            routeStartStop.text = "Jalan kaki menuju $objectName"
            artEnd.visibility = View.VISIBLE
            container.layoutParams.height =
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 160F, context.resources.displayMetrics).toInt()
        }

        if (route.Transport == null) {
            routeTransport.text = "Jalan kaki"
            artLine.setImageResource(R.drawable.dash_line)
        } else {
            routeTransport.text = route.Transport?.Name
            artLine.setImageResource(R.color.colorPrimary)
        }

        val inKm = if (route.DistanceMeters > 1000) "${route.DistanceMeters/1000} Km"
        else "${route.DistanceMeters} m"
        routeDistance.text = inKm
        routeDuration.text = "Kurang lebih ${route.DurationMinutes} menit"

        Picasso.get().load(route.IconUrl).into(artTransport)

    }

    class RouteItemUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {
                id = R.id.container
                lparams(matchParent, dip(130))
                verticalLayout {
                    gravity = Gravity.CENTER_HORIZONTAL
                    imageView {
                        id = R.id.artTransport
                    }.lparams(dip(25), dip(25)) {
                        margin = dip(5)
                    }
                    imageView {
                        id = R.id.artLine
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams(dip(3), dip(0)) {
                        weight = 4f
                    }
                    imageView {
                        id = R.id.artEnd
                        imageResource = R.drawable.ic_end_art
                        scaleType = ImageView.ScaleType.CENTER_CROP
                    }.lparams(dip(25), dip(25)) {
                        margin = dip(5)
                    }
                }.lparams(dip(50), matchParent)

                verticalLayout {
                    textView {
                        id = R.id.startStop
                    }
                    textView {
                        id = R.id.route_transport
                    }
                    textView {
                        id = R.id.route_distance
                    }
                    textView {
                        id = R.id.route_duration
                    }
                }
            }
        }
    }
}