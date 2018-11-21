/*
 * iMainView.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 12:14 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iView

import com.utsman.mobileguide.model.firestore.Document
import java.lang.Exception

interface iMainView {
    fun onRefresh()
    fun offRefresh()
    fun showDataToRecyclerView(list: MutableList<Document>)
    fun onFailure(onFailureListener: Exception)
}