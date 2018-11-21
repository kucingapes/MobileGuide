/*
 * SingleIdHelper.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 5:37 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.helper

import com.google.firebase.firestore.FirebaseFirestore
import com.utsman.mobileguide.iNotice.iNoticeSingle
import com.utsman.mobileguide.model.firestore.Document

class SingleIdHelper(private val db: FirebaseFirestore) : iNoticeSingle {
    override fun getNoticeSingleId(onCompleteListener: iNoticeSingle.OnCompleteListener, id: String) {
        id.let {
            db.collection("lokasi").document(it).get()
                .addOnSuccessListener { task ->
                    val document = task.toObject(Document::class.java)
                    document?.let { it ->
                        onCompleteListener.onComplete(it)
                    }
                }
                .addOnFailureListener { failure -> onCompleteListener.onError(failure) }
        }
    }
}