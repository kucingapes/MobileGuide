/*
 * iSingleView.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 5:24 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iView

import com.utsman.mobileguide.model.firestore.Document
import java.lang.Exception

interface iSingleView {
    fun onPrepareById(document: Document)
    fun onPrepareError(exception: Exception)
}