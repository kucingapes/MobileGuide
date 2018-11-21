/*
 * iNoticeMain.ktt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 12:19 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iNotice

import com.utsman.mobileguide.model.firestore.Document
import java.lang.Exception

interface iNoticeMain {
    interface OnCompleteListener {
        fun onComplete(list: MutableList<Document>)
        fun onFailure(onFailureListener: Exception)
    }

    fun getNoticeMain(onCompleteListener: OnCompleteListener)


}