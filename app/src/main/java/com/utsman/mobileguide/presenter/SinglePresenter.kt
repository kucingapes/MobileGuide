/*
 * SinglePresenter.kt on MobileGuide
 * Developed by Muhammad Utsman
 * Last modified 11/21/18 5:33 PM
 * Copyright (c) 2018 kucingapes
 */

package com.utsman.mobileguide.presenter

import com.utsman.mobileguide.iNotice.iNoticeSingle
import com.utsman.mobileguide.iView.iSingleView
import com.utsman.mobileguide.model.firestore.Document
import java.lang.Exception

class SinglePresenter(private var singleView: iSingleView, private var noticeSingle: iNoticeSingle) :
        iSinglePresenter, iNoticeSingle.OnCompleteListener {
    override fun requestData(id: String) {
        noticeSingle.getNoticeSingleId(this, id)
    }

    override fun onComplete(document: Document) {
        singleView.onPrepareById(document)
    }

    override fun onError(exception: Exception) {
        singleView.onPrepareError(exception)
    }
}