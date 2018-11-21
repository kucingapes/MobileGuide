/*
 * iMainView.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 12:14 AM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.iView

import android.location.Location
import com.utsman.mobileguide.model.MyDocument
import java.lang.Exception

interface iMainView {
    fun onRefresh()
    fun offRefresh()
    fun showDataToRecyclerView(list: MutableList<MyDocument>)
    fun onFailure(onFailureListener: Exception)
}