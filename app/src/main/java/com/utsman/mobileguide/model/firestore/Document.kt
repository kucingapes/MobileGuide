/*
 * Document.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 8:51 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.model.firestore

import com.google.firebase.firestore.GeoPoint

class Document {
    var id: String = ""
    var name: String = ""
    var desc: String = ""
    var images: MutableList<String> = mutableListOf()
    var location: GeoPoint = GeoPoint(21.4359571, 39.7064577) // default in makkah

    constructor()

    constructor(id: String, name: String, desc: String, images: MutableList<String>, location: GeoPoint) {
        this.id = id
        this.name = name
        this.desc = desc
        this.images = images
        this.location = location
    }

    constructor(name: String, desc: String, images: MutableList<String>, location: GeoPoint) {
        this.name = name
        this.desc = desc
        this.images = images
        this.location = location
    }


}