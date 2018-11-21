/*
 * Transport.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/10/18 7:20 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.model.trafi

data class Transport(
    val Name: String,
    val Color: String,
    val Direction: String,
    val IconUrl: String,
    val Schedule: String,
    val TrackId: String
)