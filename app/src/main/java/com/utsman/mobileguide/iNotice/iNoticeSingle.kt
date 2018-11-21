/*
 * iNoticeSingle.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 5:30 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iNotice

import com.utsman.mobileguide.model.firestore.Document
import java.lang.Exception

interface iNoticeSingle {
    interface OnCompleteListener {
        fun onComplete(document: Document)
        fun onError(exception: Exception)
    }

    fun getNoticeSingleId(onCompleteListener: OnCompleteListener, id: String)
}