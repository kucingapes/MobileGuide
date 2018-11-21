/*
 * MainHelper.ktobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 8:04 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.helper

import android.annotation.SuppressLint
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.GeoPoint
import com.utsman.mobileguide.iNotice.iNoticeMain
import com.utsman.mobileguide.model.firestore.Document

class MainHelper : iNoticeMain {

    private var db: FirebaseFirestore? = null
    private var list : MutableList<Document> = mutableListOf()

    @SuppressLint("MissingPermission", "CheckResult")
    override fun getNoticeMain(onCompleteListener: iNoticeMain.OnCompleteListener) {
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

                        val doc = Document(id, name, desc, images, location)
                        list.add(doc)

                    }
                    onCompleteListener.onComplete(list)
                }
            }
        }?.addOnFailureListener { task -> onCompleteListener.onFailure(task) }

    }
}