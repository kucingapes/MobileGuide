/*
 * RouteSegment.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/10/18 10:32 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.model.trafi

data class RouteSegment(
    var DistanceMeters: Int,
    var DurationMinutes: Int,
    var EndPoint: EndPoint,
    var IconUrl: String,
    var OtherTransports: MutableList<Any?>?,
    var RouteSegmentType: Int,
    var Shape: String,
    var StartPoint: StartPoint,
    var StopsCount: Int,
    var Transport: Transport?,
    var WalkDistanceMeters: Int
)