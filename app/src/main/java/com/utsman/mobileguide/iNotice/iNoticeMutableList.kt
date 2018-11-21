/*
 * iNoticeMutableList.ktt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 12:19 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iNotice

import com.utsman.mobileguide.model.MyDocument
import java.lang.Exception

interface iNoticeMutableList {
    interface OnCompleteListener {
        fun onComplete(list: MutableList<MyDocument>)
        fun onFailure(onFailureListener: Exception)
    }

    fun getNoticeMutableList(onCompleteListener: OnCompleteListener)


}