/*
 * Route.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/10/18 10:32 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.model.trafi

data class Route(
    var ArrivalTime: String,
    var Currency: String,
    var DepartureTime: String,
    var DurationMinutes: Int,
    var PreferenceLabel: String,
    var Price: Double,
    var RouteSegments: MutableList<RouteSegment>,
    var WalkMinutes: Int
)