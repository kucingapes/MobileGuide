/*
 * FirestoreHelper.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 8:04 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.helper

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.utsman.mobileguide.iNotice.iNoticeMutableList
import com.utsman.mobileguide.model.MyDocument

class FirestoreHelper : iNoticeMutableList {

    private var db: FirebaseFirestore? = null
    private var list : MutableList<MyDocument> = mutableListOf()

    @SuppressLint("MissingPermission", "CheckResult")
    override fun getNoticeMutableList(onCompleteListener: iNoticeMutableList.OnCompleteListener) {
        db = FirebaseFirestore.getInstance()
        db?.collection("lokasi")?.get()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (task.result != null) {
                    for (document in task.result!!) {

                        val id = document.id
                        val name = document["name"] as String
                        val desc = document["desc"] as String
                        val images = document["images"] as MutableList<String>
                        val location = document["location"] as GeoPoint

                        val doc = MyDocument(id, name, desc, images, location)
                        list.add(doc)

                    }
                    onCompleteListener.onComplete(list)
                }
            }
        }?.addOnFailureListener { task -> onCompleteListener.onFailure(task) }

    }
}